package com.devtracker.trackingservice.service.consumer;

import com.devtracker.common.constant.RabbitMQConstants;
import com.devtracker.common.event.LogCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.channels.Channel;

@Slf4j
@Component
@RabbitListener(
        queues = RabbitMQConstants.LOG_QUEUE,
        errorHandler = "globalErrorHandler",
        containerFactory = "rabbitListenerContainerFactory"
)
public class LogEventConsumer {
    @RabbitHandler
    public void handleLogCreatedEvent(LogCreatedEvent event, Channel channel, Message message) {
        String correlationId = (String)message.getMessageProperties().getHeaders().get("correlationId");
        log.info("Correlation Id: {} Handle log created event triggered", correlationId);
        // do something
    }
}
