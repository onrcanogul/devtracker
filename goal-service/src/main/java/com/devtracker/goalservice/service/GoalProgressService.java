package com.devtracker.goalservice.service;

import com.devtracker.common.event.GoalProgressEvaluationEvent;
import com.devtracker.common.service.BaseService;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.goalservice.dto.GoalProgressDto;
import com.devtracker.goalservice.entity.GoalProgress;

public interface GoalProgressService extends BaseService<GoalProgress, GoalProgressDto> {
    ServiceResponse<GoalProgressDto> handleProgress(GoalProgressEvaluationEvent event);
}
