package com.devtracker.userservice.mapper;

import com.devtracker.common.mapper.Mapper;
import com.devtracker.userservice.dto.UserDto;
import com.devtracker.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDto> {
    @Override
    public UserDto toDto(User entity) {
        if (entity == null) return null;

        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUpdatedDate(entity.getUpdatedDate());

        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setGithubUsername(entity.getGithubUsername());

        return dto;
    }

    @Override
    public User toEntity(UserDto dto) {
        if (dto == null) return null;

        User entity = new User();
        entity.setId(dto.getId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setUpdatedDate(dto.getUpdatedDate());

        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setGithubUsername(dto.getGithubUsername());

        return entity;
    }
}
