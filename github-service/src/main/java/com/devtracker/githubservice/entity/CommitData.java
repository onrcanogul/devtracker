package com.devtracker.githubservice.entity;


import com.devtracker.common.model.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
public class CommitData extends BaseEntity {
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