package com.devtracker.githubservice.service.impl;

import com.devtracker.common.mapper.Mapper;
import com.devtracker.common.repository.BaseRepository;
import com.devtracker.common.service.impl.BaseServiceImpl;
import com.devtracker.githubservice.dto.CommitDataDto;
import com.devtracker.githubservice.entity.CommitData;
import org.springframework.stereotype.Service;

@Service
public class CommitDataService extends BaseServiceImpl<CommitData, CommitDataDto> implements com.devtracker.githubservice.service.CommitDataService {
    public CommitDataService(
            BaseRepository<CommitData> repository,
            Mapper<CommitData, CommitDataDto> mapper) {
        super(repository, mapper);
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
}
