package com.devtracker.logservice.service.impl;

import com.devtracker.common.constant.RabbitMQConstants;
import com.devtracker.common.event.LogCreatedEvent;
import com.devtracker.logservice.service.EventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventPublisherImpl implements EventPublisher {
    private final RabbitTemplate rabbitTemplate;

    public EventPublisherImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishLogCreatedEvent(LogCreatedEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMQConstants.LOG_EXCHANGE,
                RabbitMQConstants.LOG_CREATED_ROUTING_KEY,
                event);
    }
}
