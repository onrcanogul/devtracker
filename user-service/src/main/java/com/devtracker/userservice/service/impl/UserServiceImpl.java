package com.devtracker.userservice.service.impl;

import com.devtracker.common.exception.BadRequestException;
import com.devtracker.userservice.dto.LoginDto;
import com.devtracker.userservice.dto.RegisterDto;
import com.devtracker.userservice.dto.UserDto;
import com.devtracker.userservice.entity.User;
import com.devtracker.userservice.mapper.UserMapper;
import com.devtracker.userservice.repository.UserRepository;
import com.devtracker.userservice.service.UserService;
import com.devtracker.common.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public UserDto register(RegisterDto request) {
        validations(request);
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public String login(LoginDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return jwtTokenProvider.generateToken(user.getEmail());
    }


    @Override
    public UserDto getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return userMapper.toDto(user);
    }

    private void validations(RegisterDto user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new BadRequestException("emailAlreadyExist");
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new BadRequestException("usernameAlreadyExist");
        }
    }
}
