package com.devtracker.githubservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;


import java.time.Instant;
import java.util.UUID;

@Entity
@Getter @Setter
public class OutboxEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String aggregateType;
    private String aggregateId;

    private String type;
    @Basic(fetch = FetchType.EAGER)
    @Column(columnDefinition = "TEXT")
    private String payload;

    private boolean published = false;
    private Instant createdAt = Instant.now();
}
