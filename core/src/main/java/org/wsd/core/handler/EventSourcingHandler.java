package org.wsd.core.handler;

import org.wsd.core.aggregate.AggregateRoot;

import java.util.UUID;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregateRoot);

    T getById(UUID aggregateRootId);
}
