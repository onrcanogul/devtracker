package com.devtracker.goalservice.repository;

import com.devtracker.common.repository.BaseRepository;
import com.devtracker.goalservice.entity.Goal;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends BaseRepository<Goal> {
}
