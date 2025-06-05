package com.devtracker.trackingservice.dto;

import com.devtracker.common.model.dto.BaseDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class DailyTrackingDto extends BaseDto {

    private Long userId;
    private LocalDate date;
    private int logCount;
    private double averageQualityScore;
    private Set<String> tagsUsed;
    private int commitCount;
    private boolean hasInsight;
    private String goalStatus;
}
