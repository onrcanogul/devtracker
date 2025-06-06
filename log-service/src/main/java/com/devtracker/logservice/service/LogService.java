package com.devtracker.logservice.service;

import com.devtracker.common.service.BaseService;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.logservice.dto.LogDto;
import com.devtracker.logservice.entity.Log;

public interface LogService extends BaseService<Log, LogDto> {
    ServiceResponse<LogDto> create(LogDto dto);
}
