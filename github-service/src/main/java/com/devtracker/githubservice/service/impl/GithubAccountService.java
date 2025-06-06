package com.devtracker.githubservice.service.impl;

import com.devtracker.common.mapper.Mapper;
import com.devtracker.common.repository.BaseRepository;
import com.devtracker.common.service.impl.BaseServiceImpl;
import com.devtracker.githubservice.dto.GithubAccountDto;
import com.devtracker.githubservice.entity.GithubAccount;
import org.springframework.stereotype.Service;

@Service
public class GithubAccountService extends BaseServiceImpl<GithubAccount, GithubAccountDto> implements com.devtracker.githubservice.service.GithubAccountService {

    public GithubAccountService(
            BaseRepository<GithubAccount> repository,
            Mapper<GithubAccount, GithubAccountDto> mapper) {
        super(repository, mapper);
    }

    @Override
    protected void updateEntity(GithubAccountDto dto, GithubAccount entity) {
        entity.setUserId(dto.getUserId());
        entity.setConnectedDate(dto.getConnectedDate());
        entity.setGithubUsername(dto.getGithubUsername());
        entity.setAccessToken(dto.getAccessToken());
        entity.setActive(dto.isActive());
    }
}
