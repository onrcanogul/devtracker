package com.devtracker.insightservice.service.impl;

import com.devtracker.common.constant.RabbitMQConstants;
import com.devtracker.common.event.LogCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.nio.channels.Channel;

@Slf4j
@RabbitListener(queues = RabbitMQConstants.LOG_QUEUE)
public class LogEventConsumer {
    @RabbitHandler
    public void handleLogCreatedEvent(LogCreatedEvent event, Channel channel, Message message) {
        String correlationId = (String)message.getMessageProperties().getHeaders().get("correlationId");
        log.info("Correlation Id: {} Handle log created event triggered", correlationId);
        // do something
    }
}
