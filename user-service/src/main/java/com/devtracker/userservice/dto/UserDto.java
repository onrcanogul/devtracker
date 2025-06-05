package com.devtracker.userservice.dto;

import com.devtracker.common.model.dto.BaseDto;
import lombok.Data;

@Data
public class UserDto extends BaseDto {
    private String username;
    private String email;
    private String githubUsername;
}
