package com.devtracker.insightservice.service;

import com.devtracker.common.event.AnalyzeCreatedEvent;

public interface EventPublisher {
    void publishLogAnalyzedEvent(AnalyzeCreatedEvent event);
}
