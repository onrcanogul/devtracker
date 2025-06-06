package com.devtracker.githubservice.dto;

import com.devtracker.common.model.dto.BaseDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class GithubAccountDto extends BaseDto {
    private UUID userId;
    private LocalDate connectedDate;
    private String githubUsername;
    private String accessToken;
    private boolean isActive;
}
