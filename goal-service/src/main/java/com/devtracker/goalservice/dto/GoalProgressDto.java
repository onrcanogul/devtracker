package com.devtracker.goalservice.dto;

import com.devtracker.common.model.dto.BaseDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GoalProgressDto extends BaseDto {

    private Long goalId;
    private LocalDate date;
    private int progress;
    private boolean completed;
}
