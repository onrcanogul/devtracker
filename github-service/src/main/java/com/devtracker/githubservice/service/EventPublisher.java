package com.devtracker.githubservice.service;

import com.devtracker.common.event.CommitCreatedEvent;

public interface EventPublisher {
    void publishCommitCreatedEvent(CommitCreatedEvent event);
}
