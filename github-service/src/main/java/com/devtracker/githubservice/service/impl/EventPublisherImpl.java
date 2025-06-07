package com.devtracker.githubservice.service.impl;


import com.devtracker.common.constant.RabbitMQConstants;
import com.devtracker.common.event.CommitCreatedEvent;
import com.devtracker.githubservice.service.EventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.stereotype.Service;

@Service
public class EventPublisherImpl implements EventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public EventPublisherImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishCommitCreatedEvent(CommitCreatedEvent event) {
        rabbitTemplate.convertAndSend(RabbitMQConstants.COMMIT_EXCHANGE,
                RabbitMQConstants.COMMIT_CREATED_ROUTING_KEY,
                event);
    }
}
