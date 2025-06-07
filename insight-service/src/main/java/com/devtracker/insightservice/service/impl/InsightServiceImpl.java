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
import com.devtracker.insightservice.entity.OutboxEvent;
import com.devtracker.insightservice.repository.OutboxRepository;
import com.devtracker.insightservice.service.EventPublisher;
import com.devtracker.insightservice.service.InsightService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;


@Service
public class InsightServiceImpl extends BaseServiceImpl<Insight, InsightDto> implements InsightService {
    private final RestTemplateBuilder restTemplateBuilder;
    private final ObjectMapper objectMapper;
    private final OutboxRepository outboxRepository;
    public InsightServiceImpl(
            BaseRepository<Insight> repository,
            Mapper<Insight, InsightDto> mapper, RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper, OutboxRepository outboxRepository) {
        super(repository, mapper);
        this.restTemplateBuilder = restTemplateBuilder;
        this.objectMapper = objectMapper;
        this.outboxRepository = outboxRepository;
    }

    @Value("${ai.service.url}")
    private String aiServiceUrl;

    @Transactional
    public ServiceResponse<Insight> analyzeLog(LogCreatedEvent event) throws JsonProcessingException {
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
        OutboxEvent outboxEvent = new OutboxEvent();
        outboxEvent.setAggregateId(analyzedEvent.getId().toString());
        outboxEvent.setType(analyzedEvent.getClass().getTypeName());
        outboxEvent.setAggregateType("Insight");
        outboxEvent.setPayload(objectMapper.writeValueAsString(analyzedEvent));
        outboxRepository.save(outboxEvent);
        return ServiceResponse.success(response.getBody(), 200);
    }

    private String serializeEvent(Object event) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Event serialization failed", e);
        }
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
