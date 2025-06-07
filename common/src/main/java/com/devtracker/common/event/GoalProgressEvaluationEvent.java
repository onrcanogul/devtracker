package com.devtracker.common.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter @Setter
public class GoalProgressEvaluationEvent extends BaseEvent {
    private UUID goalId;
    private UUID userId;
    private UUID insightId;
    private double qualityScore;
    private int linesChanged;
    private List<String> weakTags;
    private List<String> strongTags;
    private boolean consideredProgress;
}
