package com.devtracker.common.repository;

import com.devtracker.common.model.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, UUID> {
}
