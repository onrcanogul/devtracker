package com.devtracker.goalservice.service.impl;

import com.devtracker.common.mapper.Mapper;
import com.devtracker.common.repository.BaseRepository;
import com.devtracker.common.service.impl.BaseServiceImpl;
import com.devtracker.goalservice.dto.GoalProgressDto;
import com.devtracker.goalservice.entity.GoalProgress;
import com.devtracker.goalservice.repository.GoalProgressRepository;
import com.devtracker.goalservice.service.GoalProgressService;
import org.springframework.stereotype.Service;

@Service
public class GoalProgressServiceImpl extends BaseServiceImpl<GoalProgress, GoalProgressDto> implements GoalProgressService {
    public GoalProgressServiceImpl(
            GoalProgressRepository repository,
            Mapper<GoalProgress, GoalProgressDto> mapper
    ) {
        super(repository, mapper);
    }

    @Override
    protected void updateEntity(GoalProgressDto dto, GoalProgress entity) {
        entity.setGoalId(dto.getGoalId());
        entity.setDate(dto.getDate());
        entity.setProgress(dto.getProgress());
        entity.setCompleted(dto.isCompleted());
    }
}
