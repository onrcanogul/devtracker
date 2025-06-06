package com.devtracker.logservice.service.impl;

import com.devtracker.common.constant.RabbitMQConstants;
import com.devtracker.common.event.CommitCreatedEvent;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.logservice.dto.LogDto;
import com.devtracker.logservice.service.LogService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RabbitListener(queues = RabbitMQConstants.COMMIT_QUEUE)
public class CommitEventConsumer {
    private final LogService service;

    public CommitEventConsumer(LogService service) {
        this.service = service;
    }

    // handle events
    @RabbitHandler
    public void handleCommitCreated(CommitCreatedEvent event, Channel channel, Message message) {
        String correlationId = (String)message.getMessageProperties().getHeaders().get("correlationId");
        log.info("Correlation Id: {}. Commit event received for userId: {}", correlationId, event.getUserId());
        ServiceResponse<LogDto> response = service.create(getCreateModel(event));
        log.info("Log has been created. Id: {}", response.getData().getId());
    }


    // build models
    private LogDto getCreateModel(CommitCreatedEvent event) {
        LogDto dto = new LogDto();
        dto.setUserId(event.getUserId() != null ? UUID.fromString(event.getUserId().toString()) : null);
        dto.setRepositoryName(event.getRepoName());
        dto.setTitle("Commit: " + event.getMessage());
        dto.setContent("Commit SHA: " + event.getCommitSha() + "\nBranch: " + event.getBranch());
        dto.setLinesChanged(event.getLinesAdded() + event.getLinesDeleted());

        dto.setTags(List.of("auto-generated", "commit"));
        dto.setCommitRefs(List.of(event.getCommitSha()));
        dto.setQualityScore(calculateQualityScore(event));
        return dto;
    }

    private static double calculateQualityScore(CommitCreatedEvent event) {
        int netLines = event.getLinesAdded() + event.getLinesDeleted();
        if (netLines == 0) return 0.0;
        return Math.min(100.0, (event.getFilesChanged() * 10.0) + (event.isHasPullRequest() ? 20.0 : 0));
    }

}
