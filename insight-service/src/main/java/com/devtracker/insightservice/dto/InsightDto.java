package com.devtracker.insightservice.dto;


import com.devtracker.common.model.dto.BaseDto;
import lombok.Data;

import java.util.List;

@Data
public class InsightDto extends BaseDto {

    private Long userId;

    private String topTags;

    private String weakTags;

    private String activityPattern;

    private String suggestionSummary;

    private String aiTip;

    private List<Double> qualityTrend;
}
