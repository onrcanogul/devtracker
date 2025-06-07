package com.devtracker.insightservice.service.consumer;

import com.devtracker.common.constant.RabbitMQConstants;
import com.devtracker.common.event.GoalProgressEvaluationEvent;
import com.devtracker.common.event.LogCreatedEvent;
import com.devtracker.common.mapper.Mapper;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.insightservice.dto.InsightDto;
import com.devtracker.insightservice.entity.Insight;
import com.devtracker.insightservice.service.EventPublisher;
import com.devtracker.insightservice.service.InsightService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


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
    private final EventPublisher eventPublisher;

    public LogEventConsumer(InsightService insightService, Mapper<Insight, InsightDto> mapper, EventPublisher eventPublisher) {
        this.insightService = insightService;
        this.mapper = mapper;
        this.eventPublisher = eventPublisher;
    }

    @RabbitHandler
    public void handleLogCreatedEvent(LogCreatedEvent event, Channel channel, Message message) throws JsonProcessingException {
        String correlationId = (String)message.getMessageProperties().getHeaders().get("correlationId");
        log.info("Correlation Id: {} Handle log created event triggered", correlationId);

        ServiceResponse<Insight> response = insightService.analyzeLog(event);
        log.info("Correlation Id: {} Log Created Event was analyzed", correlationId);

        insightService.create(mapper.toDto(response.getData()));
        log.info("Correlation Id: {} Insight was created", correlationId);
        eventPublisher.publishGoalProcessEvaluationEvent(getProgressEvent(event, response.getData()));
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