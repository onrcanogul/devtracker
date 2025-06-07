package com.devtracker.goalservice.repository;

import com.devtracker.common.repository.BaseRepository;
import com.devtracker.goalservice.entity.GoalProgress;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GoalProgressRepository extends BaseRepository<GoalProgress> {
    Optional<GoalProgress> findByGoalId(UUID goalId);
}
