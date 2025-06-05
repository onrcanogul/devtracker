package com.devtracker.trackingservice.service.impl;

import com.devtracker.common.mapper.Mapper;

import com.devtracker.common.service.impl.BaseServiceImpl;
import com.devtracker.trackingservice.dto.TrackingSummaryDto;
import com.devtracker.trackingservice.entity.TrackingSummary;
import com.devtracker.trackingservice.repository.TrackingSummaryRepository;
import com.devtracker.trackingservice.service.TrackingSummaryService;
import org.springframework.stereotype.Service;

@Service
public class TrackingSummaryServiceImpl extends BaseServiceImpl<TrackingSummary, TrackingSummaryDto> implements TrackingSummaryService {
    public TrackingSummaryServiceImpl(
            TrackingSummaryRepository repository,
            Mapper<TrackingSummary, TrackingSummaryDto> mapper) {
        super(repository, mapper);
    }

    @Override
    protected void updateEntity(TrackingSummaryDto dto, TrackingSummary entity) {
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
    }

}
