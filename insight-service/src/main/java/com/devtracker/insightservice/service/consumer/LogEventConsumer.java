package com.devtracker.insightservice.service.consumer;

import com.devtracker.common.constant.RabbitMQConstants;
import com.devtracker.common.event.GoalProgressEvaluationEvent;
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
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private final RabbitTemplate rabbitTemplate;
    private final InsightService insightService;
    private final Mapper<Insight, InsightDto> mapper;

    public LogEventConsumer(RabbitTemplate rabbitTemplate, InsightService insightService, Mapper<Insight, InsightDto> mapper) {
        this.rabbitTemplate = rabbitTemplate;
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

        rabbitTemplate.convertAndSend(RabbitMQConstants.LOG_EXCHANGE, RabbitMQConstants.LOG_GOAL_PROGRESS_EVALUATION_ROUTING_KEY, getProgressEvent(event, response.getData()));
        log.info("Correlation Id: {} Send Goal Progress Event to Goal", correlationId);
    }

    private GoalProgressEvaluationEvent getProgressEvent(LogCreatedEvent event, Insight insight) {
        return GoalProgressEvaluationEvent.builder()
                .goalId(event.getRelatedGoalId())
                .linesChanged(event.getLinesChanged())
                .qualityScore(event.getQualityScore())
                .userId(event.getUserId())
                .weakTags(insight.getWeakTags())
                .strongTags(insight.getTopTags())
                .consideredProgress(event.getQualityScore() > 50)
                .build();
    }
}