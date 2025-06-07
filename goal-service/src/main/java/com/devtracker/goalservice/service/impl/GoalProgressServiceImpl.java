package com.devtracker.goalservice.service.impl;

import com.devtracker.common.event.GoalProgressEvaluationEvent;
import com.devtracker.common.exception.NotFoundException;
import com.devtracker.common.mapper.Mapper;
import com.devtracker.common.service.impl.BaseServiceImpl;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.goalservice.dto.GoalProgressDto;
import com.devtracker.goalservice.entity.Goal;
import com.devtracker.goalservice.entity.GoalProgress;
import com.devtracker.goalservice.repository.GoalProgressRepository;
import com.devtracker.goalservice.repository.GoalRepository;
import com.devtracker.goalservice.service.GoalProgressService;
import org.springframework.stereotype.Service;

@Service
public class GoalProgressServiceImpl extends BaseServiceImpl<GoalProgress, GoalProgressDto> implements GoalProgressService {
    private final GoalProgressRepository repository;
    private final Mapper<GoalProgress, GoalProgressDto> mapper;
    private final GoalRepository goalRepository;
    public GoalProgressServiceImpl(
            GoalProgressRepository repository,
            Mapper<GoalProgress, GoalProgressDto> mapper,
            GoalRepository goalRepository
            ) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.goalRepository = goalRepository;
    }

    public ServiceResponse<GoalProgressDto> handleProgress(GoalProgressEvaluationEvent event) {
        GoalProgress goalProgress = repository.findByGoalId(event.getGoalId())
                .orElseThrow(
                        () -> new NotFoundException("goalProgressNotFound")
                );
        Goal goal = goalRepository.findById(event.getGoalId())
                .orElseThrow(
                        () -> new NotFoundException("goalNotFound")
                );

        goalProgress.setProgress(goalProgress.getProgress() + calculateProgress(event, goal));
        GoalProgress savedProgress = repository.save(goalProgress);
        return ServiceResponse.success(mapper.toDto(savedProgress), 200);
    }

    @Override
    protected void updateEntity(GoalProgressDto dto, GoalProgress entity) {
        entity.setGoalId(dto.getGoalId());
        entity.setDate(dto.getDate());
        entity.setProgress(dto.getProgress());
        entity.setCompleted(dto.isCompleted());
    }

    private int calculateProgress(GoalProgressEvaluationEvent event, Goal goal) {
        int progress = 10;
        if (event.getQualityScore() > 50) {
            progress += 5;
        }
        int negativeMatchCount = (int) event.getWeakTags()
                .stream().filter(goal.getTags()::contains).count();
        int positiveMatchCount = (int) event.getStrongTags()
                .stream().filter(goal.getTags()::contains).count();
        progress += positiveMatchCount * 2;
        progress -= negativeMatchCount * 2;
        return progress;
    }
}
