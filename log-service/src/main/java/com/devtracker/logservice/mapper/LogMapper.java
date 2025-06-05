package com.devtracker.logservice.mapper;

import com.devtracker.common.mapper.Mapper;
import com.devtracker.logservice.dto.LogDto;
import com.devtracker.logservice.entity.Log;
import org.springframework.stereotype.Component;

@Component
public class LogMapper implements Mapper<Log, LogDto> {

    @Override
    public LogDto toDto(Log entity) {
        if (entity == null) return null;

        LogDto dto = new LogDto();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());

        dto.setUserId(entity.getUserId());
        dto.setRelatedGoalId(entity.getRelatedGoalId());
        dto.setContent(entity.getContent());
        dto.setTitle(entity.getTitle());
        dto.setRepositoryName(entity.getRepositoryName());
        dto.setTags(entity.getTags());
        dto.setCommitRefs(entity.getCommitRefs());
        dto.setQualityScore(entity.getQualityScore());
        dto.setLinesChanged(entity.getLinesChanged());

        return dto;
    }

    @Override
    public Log toEntity(LogDto dto) {
        if (dto == null) return null;

        Log entity = new Log();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUpdatedDate(dto.getUpdatedDate());

        entity.setUserId(dto.getUserId());
        entity.setRelatedGoalId(dto.getRelatedGoalId());
        entity.setContent(dto.getContent());
        entity.setTitle(dto.getTitle());
        entity.setRepositoryName(dto.getRepositoryName());
        entity.setTags(dto.getTags());
        entity.setCommitRefs(dto.getCommitRefs());
        entity.setQualityScore(dto.getQualityScore());
        entity.setLinesChanged(dto.getLinesChanged());

        return entity;
    }
}
