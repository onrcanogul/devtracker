package com.devtracker.common.event;

import java.util.UUID;

public abstract class BaseEvent {
    private final UUID idempotentToken = UUID.randomUUID();
}
