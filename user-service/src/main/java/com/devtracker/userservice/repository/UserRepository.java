package com.devtracker.userservice.repository;

import com.devtracker.common.repository.BaseRepository;
import com.devtracker.userservice.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
