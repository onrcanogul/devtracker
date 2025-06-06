package com.devtracker.githubservice.service.impl;

import com.devtracker.common.event.CommitCreatedEvent;
import com.devtracker.common.mapper.Mapper;
import com.devtracker.common.repository.BaseRepository;
import com.devtracker.common.service.impl.BaseServiceImpl;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.githubservice.dto.CommitDataDto;
import com.devtracker.githubservice.entity.CommitData;
import com.devtracker.githubservice.repository.CommitDataRepository;
import org.springframework.stereotype.Service;

@Service
public class CommitDataService extends BaseServiceImpl<CommitData, CommitDataDto> implements com.devtracker.githubservice.service.CommitDataService {
    private final CommitDataRepository repository;
    private final Mapper<CommitData, CommitDataDto> mapper;
    private final EventPublisher eventPublisher;
    public CommitDataService(
            CommitDataRepository repository,
            Mapper<CommitData, CommitDataDto> mapper, EventPublisher eventPublisher) {
        super(repository, mapper);
        this.mapper = mapper;
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ServiceResponse<CommitDataDto> create(CommitDataDto model) {
        CommitData data = mapper.toEntity(model);
        CommitData saved = repository.save(data);
        eventPublisher.publishCommitCreatedEvent(getCreatedEvent(saved));
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
