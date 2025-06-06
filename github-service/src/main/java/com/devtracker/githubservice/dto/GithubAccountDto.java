package com.devtracker.githubservice.dto;

import com.devtracker.common.model.dto.BaseDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GithubAccountDto extends BaseDto {
    private Long userId;
    private LocalDate connectedDate;
    private String githubUsername;
    private String accessToken;
    private boolean isActive;
}
