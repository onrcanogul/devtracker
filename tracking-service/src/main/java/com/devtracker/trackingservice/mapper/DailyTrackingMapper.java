package com.devtracker.trackingservice.mapper;

import com.devtracker.common.mapper.Mapper;
import com.devtracker.trackingservice.dto.DailyTrackingDto;
import com.devtracker.trackingservice.entity.DailyTracking;
import org.springframework.stereotype.Component;

@Component
public class DailyTrackingMapper implements Mapper<DailyTracking, DailyTrackingDto> {

    @Override
    public DailyTrackingDto toDto(DailyTracking entity) {
        if (entity == null) return null;

        DailyTrackingDto dto = new DailyTrackingDto();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());

        dto.setUserId(entity.getUserId());
        dto.setDate(entity.getDate());
        dto.setLogCount(entity.getLogCount());
        dto.setAverageQualityScore(entity.getAverageQualityScore());
        dto.setTagsUsed(entity.getTagsUsed());
        dto.setCommitCount(entity.getCommitCount());
        dto.setHasInsight(entity.isHasInsight());
        dto.setGoalStatus(entity.getGoalStatus());

        return dto;
    }

    @Override
    public DailyTracking toEntity(DailyTrackingDto dto) {
        if (dto == null) return null;

        DailyTracking entity = new DailyTracking();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUpdatedDate(dto.getUpdatedDate());

        entity.setUserId(dto.getUserId());
        entity.setDate(dto.getDate());
        entity.setLogCount(dto.getLogCount());
        entity.setAverageQualityScore(dto.getAverageQualityScore());
        entity.setTagsUsed(dto.getTagsUsed());
        entity.setCommitCount(dto.getCommitCount());
        entity.setHasInsight(dto.isHasInsight());
        entity.setGoalStatus(dto.getGoalStatus());

        return entity;
    }
}
