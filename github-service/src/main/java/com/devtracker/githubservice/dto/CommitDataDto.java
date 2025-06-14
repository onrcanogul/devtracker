package com.devtracker.githubservice.dto;


import com.devtracker.common.model.dto.BaseDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CommitDataDto extends BaseDto {

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
