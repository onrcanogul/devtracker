package com.devtracker.goalservice.entity;

import com.devtracker.common.model.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
public class GoalProgress extends BaseEntity {
    private UUID goalId;
    private LocalDate date;
    private int progress;
    private boolean completed;
}
