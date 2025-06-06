package com.devtracker.insightservice.repository;


import com.devtracker.common.repository.BaseRepository;
import com.devtracker.insightservice.entity.Insight;
import org.springframework.stereotype.Repository;

@Repository
public interface InsightRepository extends BaseRepository<Insight> {
}