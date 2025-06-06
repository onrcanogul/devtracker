package com.devtracker.goalservice.entity;


import com.devtracker.common.model.entity.BaseEntity;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Goal extends BaseEntity {

    private Long userId;

    private String description;

    private int targetCount;

    private String period; // örn: DAILY, WEEKLY, MONTHLY

    @ElementCollection
    private Set<String> tags;

    private LocalDate startDate;
    private LocalDate endDate;
}
