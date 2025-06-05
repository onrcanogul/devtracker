package com.devtracker.logservice.entity;

import com.devtracker.common.model.entity.BaseEntity;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Log extends BaseEntity {
    private UUID userId;
    private UUID relatedGoalId;
    private String content;
    private String title;
    private String repositoryName;
    @ElementCollection
    private List<String> tags = new ArrayList<>();
    @ElementCollection
    private List<String> commitRefs = new ArrayList<>();
    private double qualityScore;
    private int linesChanged;
}
