package com.devtracker.insightservice.dto;


import com.devtracker.common.model.dto.BaseDto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class InsightDto extends BaseDto {

    private UUID userId;

    private List<String> topTags;

    private List<String> weakTags;

    private String activityPattern;

    private String suggestionSummary;

    private String aiTip;

    private List<Double> qualityTrend;
}
