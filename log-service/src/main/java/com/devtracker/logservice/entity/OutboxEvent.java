package com.devtracker.logservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Column(columnDefinition = "TEXT")
    private String payload;

    private boolean published = false;
    private Instant createdAt = Instant.now();
}
