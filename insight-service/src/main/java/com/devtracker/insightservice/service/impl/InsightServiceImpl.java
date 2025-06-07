package com.devtracker.insightservice.service.impl;

import com.devtracker.common.event.AnalyzeCreatedEvent;
import com.devtracker.common.event.LogCreatedEvent;
import com.devtracker.common.exception.NotFoundException;
import com.devtracker.common.mapper.Mapper;
import com.devtracker.common.repository.BaseRepository;
import com.devtracker.common.service.impl.BaseServiceImpl;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.insightservice.dto.InsightDto;
import com.devtracker.insightservice.entity.Insight;
import com.devtracker.insightservice.service.InsightService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;


@Service
public class InsightServiceImpl extends BaseServiceImpl<Insight, InsightDto> implements InsightService {
    private final RestTemplateBuilder restTemplateBuilder;
    private final EventPublisher eventPublisher;
    public InsightServiceImpl(
            BaseRepository<Insight> repository,
            Mapper<Insight, InsightDto> mapper, RestTemplateBuilder restTemplateBuilder, EventPublisher eventPublisher) {
        super(repository, mapper);
        this.restTemplateBuilder = restTemplateBuilder;
        this.eventPublisher = eventPublisher;
    }

    @Value("${ai.service.url}")
    private String aiServiceUrl;

    public ServiceResponse<Insight> analyzeLog(LogCreatedEvent event) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LogCreatedEvent> request = new HttpEntity<>(event, headers); //todo separate to different service
        ResponseEntity<Insight> response = restTemplateBuilder.build().exchange(
                aiServiceUrl,
                HttpMethod.POST,
                request,
                Insight.class
        );
        if (response.getBody() == null){
            throw new NotFoundException("insightNotFound");
        }
        AnalyzeCreatedEvent analyzedEvent = new AnalyzeCreatedEvent();
        BeanUtils.copyProperties(response.getBody(), analyzedEvent);
        eventPublisher.publishLogAnalyzedEvent(analyzedEvent);
        return ServiceResponse.success(response.getBody(), 200);
    }

    @Override
    protected void updateEntity(InsightDto dto, Insight entity) {
        entity.setUserId(dto.getUserId());
        entity.setTopTags(dto.getTopTags());
        entity.setWeakTags(dto.getWeakTags());
        entity.setActivityPattern(dto.getActivityPattern());
        entity.setSuggestionSummary(dto.getSuggestionSummary());
        entity.setAiTip(dto.getAiTip());
        entity.setQualityTrend(dto.getQualityTrend());
    }
}
