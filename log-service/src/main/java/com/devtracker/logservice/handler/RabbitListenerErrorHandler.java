package com.devtracker.logservice.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class RabbitListenerErrorHandler {
    @Bean
    public org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler globalErrorHandler() {
        return (amqpMessage, channel, springMessage, exception) -> {
            String correlationId = null;
            if (springMessage != null && springMessage.getHeaders().containsKey("correlationId")) {
                correlationId = springMessage.getHeaders().get("correlationId", String.class);
            }
            log.error("RabbitMQ Error | CorrelationId: {} | Exception: {}", correlationId, exception.getMessage(), exception);
            throw exception; //to DLQ
        };
    }
}

