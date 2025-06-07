package com.devtracker.common.event;

import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
public class CommitCreatedEvent extends BaseEvent {

    private UUID id;

    private Date createdDate;

    private Date updatedDate;

    private boolean isDeleted;

    private UUID userId;

    private String repoName;

    private String commitSha;

    private String message;

    private LocalDateTime timestamp;

    private int filesChanged;

    private int linesAdded;

    private int linesDeleted;

    private String branch;

    private boolean hasPullRequest;
}
