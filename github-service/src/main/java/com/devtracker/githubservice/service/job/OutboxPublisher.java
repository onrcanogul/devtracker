package com.devtracker.githubservice.service.job;

import com.devtracker.common.event.CommitCreatedEvent;
import com.devtracker.githubservice.entity.OutboxEvent;
import com.devtracker.githubservice.repository.OutboxRepository;
import com.devtracker.githubservice.service.impl.EventPublisherImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class OutboxPublisher {
    private final OutboxRepository repository;
    private final EventPublisherImpl eventPublisherImpl;
    private final ObjectMapper objectMapper;

    public OutboxPublisher(OutboxRepository repository, EventPublisherImpl eventPublisherImpl, ObjectMapper objectMapper) {
        this.repository = repository;
        this.eventPublisherImpl = eventPublisherImpl;
        this.objectMapper = objectMapper;
    }

    @Scheduled(fixedRate = 5000)
    public void publishUnsentEvents() {
        List<OutboxEvent> outboxes = repository.findByPublishedFalse();
        outboxes.forEach(outbox -> {
            try {
                eventPublisherImpl.publishCommitCreatedEvent(objectMapper.readValue(outbox.getPayload(), CommitCreatedEvent.class));
                outbox.setPublished(true);
                repository.save(outbox);
            } catch (JsonProcessingException e) {
                log.error("Error while publishing outbox events: {}", e.getMessage());
            }
        });
    }
}
