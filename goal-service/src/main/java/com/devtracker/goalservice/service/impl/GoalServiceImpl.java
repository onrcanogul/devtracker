package com.devtracker.goalservice.service.impl;

import com.devtracker.common.mapper.Mapper;
import com.devtracker.common.repository.BaseRepository;
import com.devtracker.common.service.impl.BaseServiceImpl;
import com.devtracker.goalservice.dto.GoalDto;
import com.devtracker.goalservice.entity.Goal;
import com.devtracker.goalservice.repository.GoalRepository;
import com.devtracker.goalservice.service.GoalService;
import org.springframework.stereotype.Service;

@Service
public class GoalServiceImpl extends BaseServiceImpl<Goal, GoalDto> implements GoalService {
    public GoalServiceImpl(
            GoalRepository repository,
            Mapper<Goal, GoalDto> mapper) {
        super(repository, mapper);
    }

    @Override
    protected void updateEntity(GoalDto dto, Goal entity) {
        entity.setUserId(dto.getUserId());
        entity.setDescription(dto.getDescription());
        entity.setTargetCount(dto.getTargetCount());
        entity.setPeriod(dto.getPeriod());
        entity.setTags(dto.getTags());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
    }
}
