package com.devtracker.trackingservice.mapper;

import com.devtracker.common.mapper.Mapper;
import com.devtracker.trackingservice.dto.TrackingSummaryDto;
import com.devtracker.trackingservice.entity.TrackingSummary;
import org.springframework.stereotype.Component;

@Component
public class TrackingSummaryMapper implements Mapper<TrackingSummary, TrackingSummaryDto> {

    @Override
    public TrackingSummaryDto toDto(TrackingSummary entity) {
        if (entity == null) return null;

        TrackingSummaryDto dto = new TrackingSummaryDto();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());

        dto.setUserId(entity.getUserId());
        dto.setRange(entity.getRange());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setTotalLogCount(entity.getTotalLogCount());
        dto.setTotalCommitCount(entity.getTotalCommitCount());
        dto.setGoalCompletionRate(entity.getGoalCompletionRate());
        dto.setActiveDays(entity.getActiveDays());
        dto.setAverageQualityScore(entity.getAverageQualityScore());
        dto.setTopTags(entity.getTopTags());

        return dto;
    }

    @Override
    public TrackingSummary toEntity(TrackingSummaryDto dto) {
        if (dto == null) return null;

        TrackingSummary entity = new TrackingSummary();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUpdatedDate(dto.getUpdatedDate());

        entity.setUserId(dto.getUserId());
        entity.setRange(dto.getRange());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setTotalLogCount(dto.getTotalLogCount());
        entity.setTotalCommitCount(dto.getTotalCommitCount());
        entity.setGoalCompletionRate(dto.getGoalCompletionRate());
        entity.setActiveDays(dto.getActiveDays());
        entity.setAverageQualityScore(dto.getAverageQualityScore());
        entity.setTopTags(dto.getTopTags());

        return entity;
    }
}
