package com.devtracker.insightservice.service;

import com.devtracker.common.event.AnalyzeCreatedEvent;
import com.devtracker.common.event.GoalProgressEvaluationEvent;

public interface EventPublisher {
    void publishLogAnalyzedEvent(AnalyzeCreatedEvent event);
    void publishGoalProcessEvaluationEvent(GoalProgressEvaluationEvent event);
}
