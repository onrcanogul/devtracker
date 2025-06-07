package com.devtracker.logservice.service;

import com.devtracker.common.event.LogCreatedEvent;

public interface EventPublisher {
    void publishLogCreatedEvent(LogCreatedEvent event);
}
