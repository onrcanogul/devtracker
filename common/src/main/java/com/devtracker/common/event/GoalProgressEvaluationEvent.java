package com.devtracker.common.event;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
