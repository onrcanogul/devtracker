package com.devtracker.logservice.service.impl;

import com.devtracker.common.event.LogCreatedEvent;
import com.devtracker.common.mapper.Mapper;
import com.devtracker.common.service.impl.BaseServiceImpl;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.logservice.dto.LogDto;
import com.devtracker.logservice.entity.Log;
import com.devtracker.logservice.repository.LogRepository;
import com.devtracker.logservice.service.LogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LogServiceImpl extends BaseServiceImpl<Log, LogDto> implements LogService {

    private final LogRepository repository;
    private final Mapper<Log, LogDto> mapper;
    private final EventPublisher eventPublisher;
    public LogServiceImpl(
            LogRepository repository,
            Mapper<Log, LogDto> mapper, EventPublisher eventPublisher
    ) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.eventPublisher = eventPublisher;
    }

    public ServiceResponse<LogDto> create(LogDto dto) {
        Log model = mapper.toEntity(dto);
        Log saved = repository.save(model);
        eventPublisher.publishLogCreatedEvent(getCreatedEvent(saved));
        return ServiceResponse.success(mapper.toDto(saved), 201);
    }

    @Override
    protected void updateEntity(LogDto dto, Log entity) {
        entity.setContent(dto.getContent());
        entity.setTitle(dto.getTitle());
        entity.setTags(dto.getTags());
        entity.setRelatedGoalId(dto.getRelatedGoalId());
        entity.setLinesChanged(dto.getLinesChanged());
        entity.setQualityScore(dto.getQualityScore());
        entity.setCommitRefs(dto.getCommitRefs());
        entity.setRepositoryName(dto.getRepositoryName());
    }

    private LogCreatedEvent getCreatedEvent(Log dto) {
        return LogCreatedEvent.builder()
                .id(dto.getId())
                .createdDate(dto.getCreatedDate())
                .updatedDate(dto.getUpdatedDate())
                .isDeleted(dto.isDeleted())
                .userId(dto.getUserId())
                .relatedGoalId(dto.getRelatedGoalId())
                .content(dto.getContent())
                .title(dto.getTitle())
                .repositoryName(dto.getRepositoryName())
                .tags(dto.getTags() != null ? dto.getTags() : new ArrayList<>())
                .commitRefs(dto.getCommitRefs() != null ? dto.getCommitRefs() : new ArrayList<>())
                .qualityScore(dto.getQualityScore())
                .linesChanged(dto.getLinesChanged())
                .build();
    }

}
