package org.wsd.core.eventstore;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.wsd.core.events.EventModel;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventStoreRepository extends MongoRepository<EventModel, UUID> {
    List<EventModel> findByAggregateIdentifier(UUID aggregateIdentifier);
}
