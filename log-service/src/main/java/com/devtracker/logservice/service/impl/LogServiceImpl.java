package com.devtracker.logservice.service.impl;

import com.devtracker.common.mapper.Mapper;
import com.devtracker.common.repository.BaseRepository;
import com.devtracker.common.service.impl.BaseServiceImpl;
import com.devtracker.logservice.dto.LogDto;
import com.devtracker.logservice.entity.Log;
import com.devtracker.logservice.service.LogService;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl extends BaseServiceImpl<Log, LogDto> implements LogService {

    public LogServiceImpl(
            BaseRepository<Log> repository,
            Mapper<Log, LogDto> mapper
    ) {
        super(repository, mapper);
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
}
