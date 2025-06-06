package com.devtracker.insightservice.mapper;


import com.devtracker.common.mapper.Mapper;
import com.devtracker.insightservice.dto.InsightDto;
import com.devtracker.insightservice.entity.Insight;
import org.springframework.stereotype.Component;

@Component
public class InsightMapper implements Mapper<Insight, InsightDto> {

    @Override
    public InsightDto toDto(Insight entity) {
        if (entity == null) return null;

        InsightDto dto = new InsightDto();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());

        dto.setUserId(entity.getUserId());
        dto.setTopTags(entity.getTopTags());
        dto.setWeakTags(entity.getWeakTags());
        dto.setActivityPattern(entity.getActivityPattern());
        dto.setSuggestionSummary(entity.getSuggestionSummary());
        dto.setAiTip(entity.getAiTip());
        dto.setQualityTrend(entity.getQualityTrend());

        return dto;
    }

    @Override
    public Insight toEntity(InsightDto dto) {
        if (dto == null) return null;

        Insight entity = new Insight();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUpdatedDate(dto.getUpdatedDate());

        entity.setUserId(dto.getUserId());
        entity.setTopTags(dto.getTopTags());
        entity.setWeakTags(dto.getWeakTags());
        entity.setActivityPattern(dto.getActivityPattern());
        entity.setSuggestionSummary(dto.getSuggestionSummary());
        entity.setAiTip(dto.getAiTip());
        entity.setQualityTrend(dto.getQualityTrend());

        return entity;
    }
}
