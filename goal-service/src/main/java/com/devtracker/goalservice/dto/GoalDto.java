package com.devtracker.goalservice.dto;


import com.devtracker.common.model.dto.BaseDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
public class GoalDto extends BaseDto {

    private UUID userId;
    private String description;
    private int targetCount;
    private String period;
    private Set<String> tags;
    private LocalDate startDate;
    private LocalDate endDate;
}
