package com.devtracker.trackingservice.service.impl;

import com.devtracker.common.mapper.Mapper;
import com.devtracker.common.repository.BaseRepository;
import com.devtracker.common.service.impl.BaseServiceImpl;
import com.devtracker.trackingservice.dto.DailyTrackingDto;
import com.devtracker.trackingservice.entity.DailyTracking;
import com.devtracker.trackingservice.service.DailyTrackingService;
import org.springframework.stereotype.Service;

@Service
public class DailyTrackingServiceImpl extends BaseServiceImpl<DailyTracking, DailyTrackingDto> implements DailyTrackingService {
    public DailyTrackingServiceImpl(
            BaseRepository<DailyTracking> repository,
            Mapper<DailyTracking,DailyTrackingDto> mapper) {
        super(repository, mapper);
    }

    @Override
    protected void updateEntity(DailyTrackingDto dto, DailyTracking entity) {
        entity.setUserId(dto.getUserId());
        entity.setDate(dto.getDate());
        entity.setLogCount(dto.getLogCount());
        entity.setAverageQualityScore(dto.getAverageQualityScore());
        entity.setTagsUsed(dto.getTagsUsed());
        entity.setCommitCount(dto.getCommitCount());
        entity.setHasInsight(dto.isHasInsight());
        entity.setGoalStatus(dto.getGoalStatus());
    }

}
