package com.devtracker.insightservice.service.impl;

import com.devtracker.common.mapper.Mapper;
import com.devtracker.common.repository.BaseRepository;
import com.devtracker.common.service.impl.BaseServiceImpl;
import com.devtracker.insightservice.dto.InsightDto;
import com.devtracker.insightservice.entity.Insight;
import com.devtracker.insightservice.service.InsightService;
import org.springframework.stereotype.Service;

@Service
public class InsightServiceImpl extends BaseServiceImpl<Insight, InsightDto> implements InsightService {
    public InsightServiceImpl(
            BaseRepository<Insight> repository,
            Mapper<Insight, InsightDto> mapper) {
        super(repository, mapper);
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
