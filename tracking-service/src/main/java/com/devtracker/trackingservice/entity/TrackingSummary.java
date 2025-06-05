package com.devtracker.trackingservice.entity;


import com.devtracker.common.model.entity.BaseEntity;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
public class TrackingSummary extends BaseEntity {

    private Long userId;
    private String range; // örn: "WEEKLY", "MONTHLY"
    private LocalDate startDate;
    private LocalDate endDate;
    private int totalLogCount;
    private int totalCommitCount;
    private double goalCompletionRate;
    private int activeDays;
    private double averageQualityScore;
    @ElementCollection
    private Set<String> topTags;
}
