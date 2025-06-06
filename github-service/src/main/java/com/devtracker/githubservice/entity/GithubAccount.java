package com.devtracker.githubservice.entity;

import com.devtracker.common.model.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
public class GithubAccount extends BaseEntity {

    private UUID userId;

    private LocalDate connectedDate;

    private String githubUsername;

    private String accessToken;

    private boolean isActive;
}