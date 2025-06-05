package com.devtracker.trackingservice.entity;



import com.devtracker.common.model.entity.BaseEntity;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
public class DailyTracking extends BaseEntity {

    private Long userId;

    private LocalDate date;

    private int logCount;

    private double averageQualityScore;

    @ElementCollection
    private Set<String> tagsUsed;

    private int commitCount;

    private boolean hasInsight;

//    @Enumerated(EnumType.STRING)
    private String goalStatus;
}
