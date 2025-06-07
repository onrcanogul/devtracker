package com.devtracker.common.event;


import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@Getter @Setter
public class LogCreatedEvent extends BaseEvent {
    private UUID id;
    private Date createdDate;
    private Date updatedDate;
    private boolean isDeleted;
    private UUID userId;
    private UUID relatedGoalId;
    private String content;
    private String title;
    private String repositoryName;
    private List<String> tags = new ArrayList<>();
    private List<String> commitRefs = new ArrayList<>();
    private double qualityScore;
    private int linesChanged;
}
