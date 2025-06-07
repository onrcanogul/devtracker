package com.devtracker.goalservice.service.impl;

import com.devtracker.common.constant.RabbitMQConstants;
import com.devtracker.common.event.GoalProgressEvaluationEvent;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.goalservice.dto.GoalProgressDto;
import com.devtracker.goalservice.entity.GoalProgress;
import com.devtracker.goalservice.service.GoalProgressService;
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
    private final GoalProgressService service;

    public LogEventConsumer(GoalProgressService service) {
        this.service = service;
    }

    @RabbitHandler
    public void handleGoalProgressEvaluationEvent(GoalProgressEvaluationEvent event, Channel channel, Message message) {
        String correlationId = (String) message.getMessageProperties().getHeaders().get("correlationId");
        log.info("Correlation Id: {} Handle Goal Progress Evaluation Consumer Triggered", correlationId);

        ServiceResponse<GoalProgressDto> response = service.handleProgress(event);
        log.info("Correlation Id: {} Handle Goal Progress Evaluation", correlationId);
    }
}
