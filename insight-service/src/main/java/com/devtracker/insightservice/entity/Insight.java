package com.devtracker.insightservice.entity;

import com.devtracker.common.model.entity.BaseEntity;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Insight extends BaseEntity {

    private Long userId;

    private String topTags;

    private String weakTags;

    private String activityPattern;

    private String suggestionSummary;

    private String aiTip;

    @ElementCollection
    private List<Double> qualityTrend;
}
