package com.devtracker.goalservice.mapper;

import com.devtracker.common.mapper.Mapper;
import com.devtracker.goalservice.dto.GoalProgressDto;
import com.devtracker.goalservice.entity.GoalProgress;
import org.springframework.stereotype.Component;

@Component
public class GoalProgressMapper implements Mapper<GoalProgress, GoalProgressDto> {

    @Override
    public GoalProgressDto toDto(GoalProgress entity) {
        if (entity == null) return null;

        GoalProgressDto dto = new GoalProgressDto();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());

        dto.setGoalId(entity.getGoalId());
        dto.setDate(entity.getDate());
        dto.setProgress(entity.getProgress());
        dto.setCompleted(entity.isCompleted());

        return dto;
    }

    @Override
    public GoalProgress toEntity(GoalProgressDto dto) {
        if (dto == null) return null;

        GoalProgress entity = new GoalProgress();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUpdatedDate(dto.getUpdatedDate());

        entity.setGoalId(dto.getGoalId());
        entity.setDate(dto.getDate());
        entity.setProgress(dto.getProgress());
        entity.setCompleted(dto.isCompleted());

        return entity;
    }
}
