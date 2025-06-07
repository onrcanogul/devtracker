package com.devtracker.logservice.service.job;

import com.devtracker.common.event.LogCreatedEvent;
import com.devtracker.logservice.entity.OutboxEvent;
import com.devtracker.logservice.repository.OutboxRepository;
import com.devtracker.logservice.service.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class OutboxPublisher {
    private final OutboxRepository repository;
    private final EventPublisher eventPublisherImpl;

    public OutboxPublisher(OutboxRepository repository, EventPublisher eventPublisherImpl) {
        this.repository = repository;
        this.eventPublisherImpl = eventPublisherImpl;
    }

    @Scheduled(fixedRate = 5000)
    public void publishUnsentEvents() {
        List<OutboxEvent> outboxes = repository.findByPublishedFalse();
        outboxes.forEach(outbox -> {
            try {
                eventPublisherImpl.publishLogCreatedEvent(new ObjectMapper().readValue(outbox.getPayload(), LogCreatedEvent.class));
                outbox.setPublished(true);
                repository.save(outbox);
            } catch (JsonProcessingException e) {
                log.error("Error while publishing outbox events: {}", e.getMessage());
            }
        });
    }
}
