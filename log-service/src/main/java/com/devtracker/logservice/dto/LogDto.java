package com.devtracker.logservice.dto;

import com.devtracker.common.model.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class LogDto extends BaseDto {
    private UUID userId;
    private UUID relatedGoalId;
    private String content;
    private String title;
    private String repositoryName;
    private List<String> tags = new ArrayList<>();
    private List<String> commitRefs = new ArrayList<>();
    private double qualityScore;
    private int linesChanged;
}
