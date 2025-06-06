package com.devtracker.githubservice.entity;

import com.devtracker.common.model.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
@Entity
public class GithubAccount extends BaseEntity {

    private Long userId;

    private LocalDate connectedDate;

    private String githubUsername;

    private String accessToken;

    private boolean isActive;
}