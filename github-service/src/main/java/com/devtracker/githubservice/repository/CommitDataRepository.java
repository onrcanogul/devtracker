package com.devtracker.githubservice.repository;

import com.devtracker.common.repository.BaseRepository;
import com.devtracker.githubservice.entity.CommitData;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitDataRepository extends BaseRepository<CommitData> {
}
