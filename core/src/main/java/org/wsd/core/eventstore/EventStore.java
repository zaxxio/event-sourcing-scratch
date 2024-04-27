package org.wsd.core.eventstore;

import org.wsd.core.events.BaseEvent;

import java.util.List;
import java.util.UUID;

public interface EventStore {
    void saveEvents(UUID aggregateId, Iterable<BaseEvent> events, int expectedVersion);

    List<BaseEvent> getById(UUID aggregateId);
}
