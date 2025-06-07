package com.devtracker.githubservice.service;

import com.devtracker.common.service.BaseService;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.githubservice.dto.CommitDataDto;
import com.devtracker.githubservice.entity.CommitData;

public interface CommitDataService extends BaseService<CommitData, CommitDataDto> {
    ServiceResponse<CommitDataDto> create(CommitDataDto model);
}
