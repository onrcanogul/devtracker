package com.devtracker.githubservice.repository;

import com.devtracker.common.repository.BaseRepository;
import com.devtracker.githubservice.entity.GithubAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface GithubAccountRepository extends BaseRepository<GithubAccount> {
}
