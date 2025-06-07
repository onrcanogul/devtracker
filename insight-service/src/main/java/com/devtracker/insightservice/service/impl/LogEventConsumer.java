package com.devtracker.insightservice.service.impl;

import com.devtracker.common.constant.RabbitMQConstants;
import com.devtracker.common.event.LogCreatedEvent;
import com.devtracker.common.mapper.Mapper;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.insightservice.dto.InsightDto;
import com.devtracker.insightservice.entity.Insight;
import com.devtracker.insightservice.service.InsightService;
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
    private final InsightService insightService;
    private final Mapper<Insight, InsightDto> mapper;

    public LogEventConsumer(InsightService insightService, Mapper<Insight, InsightDto> mapper) {
        this.insightService = insightService;
        this.mapper = mapper;
    }

    @RabbitHandler
    public void handleLogCreatedEvent(LogCreatedEvent event, Channel channel, Message message) {
        String correlationId = (String)message.getMessageProperties().getHeaders().get("correlationId");
        log.info("Correlation Id: {} Handle log created event triggered", correlationId);
        ServiceResponse<Insight> response = insightService.analyzeLog(event);
        log.info("Correlation Id: {} Log Created Event was analyzed", correlationId);
        insightService.create(mapper.toDto(response.getData()));
        log.info("Correlation Id: {} Insight was created", correlationId);
    }
}
