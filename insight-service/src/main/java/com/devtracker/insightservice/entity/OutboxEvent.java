package com.devtracker.insightservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter @Setter
public class OutboxEvent {
    @Id
    private UUID id;
    private String aggregateType;
    private String aggregateId;

    private String type;
    private String payload;

    private boolean published = false;
    private Instant createdAt = Instant.now();
}
