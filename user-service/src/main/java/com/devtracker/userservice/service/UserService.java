package com.devtracker.userservice.service;


import com.devtracker.userservice.dto.LoginDto;
import com.devtracker.userservice.dto.RegisterDto;
import com.devtracker.userservice.dto.UserDto;

public interface UserService {
    UserDto register(RegisterDto request);
    String login(LoginDto request);
    UserDto getCurrentUser();
}
