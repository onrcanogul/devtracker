package com.devtracker.insightservice.service.impl;

import com.devtracker.common.constant.RabbitMQConstants;
import com.devtracker.common.event.AnalyzeCreatedEvent;
import com.devtracker.insightservice.service.EventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventPublisherImpl implements EventPublisher {
    private final RabbitTemplate rabbitTemplate;


    public EventPublisherImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishLogAnalyzedEvent(AnalyzeCreatedEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitMQConstants.INSIGHT_EXCHANGE,
                RabbitMQConstants.INSIGHT_ANALYZE_CREATED_ROUTING_KEY,
                event
        );
    }
}
