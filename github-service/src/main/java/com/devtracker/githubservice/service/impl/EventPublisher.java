package com.devtracker.githubservice.service.impl;


import com.devtracker.common.constant.RabbitMQConstants;
import com.devtracker.common.event.CommitCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public EventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishCommitCreatedEvent(CommitCreatedEvent event) {
        rabbitTemplate.convertAndSend(RabbitMQConstants.COMMIT_EXCHANGE,
                RabbitMQConstants.COMMIT_CREATED_ROUTING_KEY,
                event);
    }
}
