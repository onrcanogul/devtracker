package com.devtracker.userservice.entity;

import com.devtracker.common.model.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class User extends BaseEntity {
    private String username;
    private String email;
    private String password;
    private String githubUsername;
    private String githubAccessToken;
}
