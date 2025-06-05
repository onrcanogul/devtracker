package com.devtracker.trackingservice.dto;

import com.devtracker.common.model.dto.BaseDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class TrackingSummaryDto extends BaseDto {
    private Long userId;
    private String range;
    private LocalDate startDate;
    private LocalDate endDate;
    private int totalLogCount;
    private int totalCommitCount;
    private double goalCompletionRate;
    private int activeDays;
    private double averageQualityScore;
    private Set<String> topTags;
}
