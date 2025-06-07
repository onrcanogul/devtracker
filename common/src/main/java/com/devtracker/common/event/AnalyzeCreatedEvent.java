package com.devtracker.common.event;


import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class AnalyzeCreatedEvent extends BaseEvent {
    private UUID id;
    private Date createdDate;
    private Date updatedDate;
    private boolean isDeleted;
    private Long userId;
    private String topTags;
    private String weakTags;
    private String activityPattern;
    private String suggestionSummary;
    private String aiTip;
    private List<Double> qualityTrend;
}
