package com.devtracker.githubservice.mapper;

import com.devtracker.common.mapper.Mapper;
import com.devtracker.githubservice.dto.GithubAccountDto;
import com.devtracker.githubservice.entity.GithubAccount;
import org.springframework.stereotype.Component;

@Component
public class GithubAccountMapper implements Mapper<GithubAccount, GithubAccountDto> {

    @Override
    public GithubAccountDto toDto(GithubAccount entity) {
        if (entity == null) return null;

        GithubAccountDto dto = new GithubAccountDto();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());

        dto.setUserId(entity.getUserId());
        dto.setConnectedDate(entity.getConnectedDate());
        dto.setGithubUsername(entity.getGithubUsername());
        dto.setAccessToken(entity.getAccessToken());
        dto.setActive(entity.isActive());

        return dto;
    }

    @Override
    public GithubAccount toEntity(GithubAccountDto dto) {
        if (dto == null) return null;

        GithubAccount entity = new GithubAccount();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUpdatedDate(dto.getUpdatedDate());

        entity.setUserId(dto.getUserId());
        entity.setConnectedDate(dto.getConnectedDate());
        entity.setGithubUsername(dto.getGithubUsername());
        entity.setAccessToken(dto.getAccessToken());
        entity.setActive(dto.isActive());

        return entity;
    }
}
