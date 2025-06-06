package com.devtracker.githubservice.mapper;

import com.devtracker.common.mapper.Mapper;
import com.devtracker.githubservice.dto.CommitDataDto;
import com.devtracker.githubservice.entity.CommitData;
import org.springframework.stereotype.Component;

@Component
public class CommitDataMapper implements Mapper<CommitData, CommitDataDto> {

    @Override
    public CommitDataDto toDto(CommitData entity) {
        if (entity == null) return null;

        CommitDataDto dto = new CommitDataDto();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());

        dto.setUserId(entity.getUserId());
        dto.setRepoName(entity.getRepoName());
        dto.setCommitSha(entity.getCommitSha());
        dto.setMessage(entity.getMessage());
        dto.setTimestamp(entity.getTimestamp());
        dto.setFilesChanged(entity.getFilesChanged());
        dto.setLinesAdded(entity.getLinesAdded());
        dto.setLinesDeleted(entity.getLinesDeleted());
        dto.setBranch(entity.getBranch());
        dto.setHasPullRequest(entity.isHasPullRequest());

        return dto;
    }

    @Override
    public CommitData toEntity(CommitDataDto dto) {
        if (dto == null) return null;

        CommitData entity = new CommitData();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUpdatedDate(dto.getUpdatedDate());

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

        return entity;
    }
}
