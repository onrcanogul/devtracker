package com.devtracker.githubservice.service.impl;

import com.devtracker.common.event.CommitCreatedEvent;
import com.devtracker.common.mapper.Mapper;
import com.devtracker.common.service.impl.BaseServiceImpl;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.githubservice.dto.CommitDataDto;
import com.devtracker.githubservice.entity.CommitData;
import com.devtracker.githubservice.entity.OutboxEvent;
import com.devtracker.githubservice.repository.CommitDataRepository;
import com.devtracker.githubservice.repository.OutboxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CommitDataService extends BaseServiceImpl<CommitData, CommitDataDto> implements com.devtracker.githubservice.service.CommitDataService {
    private final CommitDataRepository repository;
    private final OutboxRepository outboxRepository;
    private final Mapper<CommitData, CommitDataDto> mapper;
    private final EventPublisherImpl eventPublisherImpl;
    public CommitDataService(
            CommitDataRepository repository, OutboxRepository outboxRepository,
            Mapper<CommitData, CommitDataDto> mapper, EventPublisherImpl eventPublisherImpl) {
        super(repository, mapper);
        this.outboxRepository = outboxRepository;
        this.mapper = mapper;
        this.repository = repository;
        this.eventPublisherImpl = eventPublisherImpl;
    }

    @Transactional
    public ServiceResponse<CommitDataDto> create(CommitDataDto model) {
        CommitData data = mapper.toEntity(model);
        CommitData saved = repository.save(data);

        OutboxEvent outboxEvent = new OutboxEvent();
        CommitCreatedEvent event = getCreatedEvent(saved);
        outboxEvent.setAggregateId(saved.getId().toString());
        outboxEvent.setAggregateType("CommitData");
        outboxEvent.setPayload(serializeEvent(event));
        outboxEvent.setType(event.getClass().getTypeName());
        outboxRepository.save(outboxEvent);
        return ServiceResponse.success(mapper.toDto(saved), 201);
    }

    @Override
    protected void updateEntity(CommitDataDto dto, CommitData entity) {
        entity.setUserId(dto.getUserId());
        entity.setRepoName(dto.getRepoName());
        entity.setCommitSha(dto.getCommitSha());
        entity.setMessage(dto.getMessage());
        entity.setTimestamp(dto.getTimestamp());
        entity.setFilesChanged(dto.getFilesChanged());
        entity.setLinesAdded(dto.getLinesAdded());
        entity.setLinesDeleted(dto.getLinesDeleted());
        entity.setBranch(dto.getBranch());
        entity.setHasPullRequest(dto.isHasPullRequest());
    }

    private String serializeEvent(Object event) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Event serialization failed", e);
        }
    }

    private CommitCreatedEvent getCreatedEvent(CommitData saved) {
        return CommitCreatedEvent.builder()
                .id(saved.getId())
                .createdDate(saved.getCreatedDate())
                .updatedDate(saved.getUpdatedDate())
                .isDeleted(saved.isDeleted())
                .userId(saved.getUserId())
                .repoName(saved.getRepoName())
                .commitSha(saved.getCommitSha())
                .message(saved.getMessage())
                .timestamp(saved.getTimestamp())
                .filesChanged(saved.getFilesChanged())
                .linesAdded(saved.getLinesAdded())
                .linesDeleted(saved.getLinesDeleted())
                .branch(saved.getBranch())
                .hasPullRequest(saved.isHasPullRequest())
                .build();
    }
}
