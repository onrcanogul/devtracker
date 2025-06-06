package com.devtracker.goalservice.mapper;

import com.devtracker.common.mapper.Mapper;
import com.devtracker.goalservice.dto.GoalDto;
import com.devtracker.goalservice.entity.Goal;
import org.springframework.stereotype.Component;

@Component
public class GoalMapper implements Mapper<Goal, GoalDto> {

    @Override
    public GoalDto toDto(Goal entity) {
        if (entity == null) return null;

        GoalDto dto = new GoalDto();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());

        dto.setUserId(entity.getUserId());
        dto.setDescription(entity.getDescription());
        dto.setTargetCount(entity.getTargetCount());
        dto.setPeriod(entity.getPeriod());
        dto.setTags(entity.getTags());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());

        return dto;
    }

    @Override
    public Goal toEntity(GoalDto dto) {
        if (dto == null) return null;

        Goal entity = new Goal();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUpdatedDate(dto.getUpdatedDate());

        entity.setUserId(dto.getUserId());
        entity.setDescription(dto.getDescription());
        entity.setTargetCount(dto.getTargetCount());
        entity.setPeriod(dto.getPeriod());
        entity.setTags(dto.getTags());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());

        return entity;
    }
}
