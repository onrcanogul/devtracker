package com.devtracker.insightservice.service;

import com.devtracker.common.event.LogCreatedEvent;
import com.devtracker.common.service.BaseService;
import com.devtracker.common.util.ServiceResponse;
import com.devtracker.insightservice.dto.InsightDto;
import com.devtracker.insightservice.entity.Insight;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface InsightService extends BaseService<Insight, InsightDto> {
    ServiceResponse<Insight> analyzeLog(LogCreatedEvent event) throws JsonProcessingException;
}
