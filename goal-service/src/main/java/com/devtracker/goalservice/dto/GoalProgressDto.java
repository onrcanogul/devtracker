package com.devtracker.goalservice.dto;

import com.devtracker.common.model.dto.BaseDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class GoalProgressDto extends BaseDto {

    private UUID goalId;
    private LocalDate date;
    private int progress;
    private boolean completed;
}
