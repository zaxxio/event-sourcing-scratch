package org.wsd.app.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.stereotype.Service;
import org.wsd.app.aggregate.AccountAggregate;
import org.wsd.core.events.BaseEvent;
import org.wsd.core.events.EventModel;
import org.wsd.core.eventstore.EventStore;
import org.wsd.core.eventstore.EventStoreRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountEventStore implements EventStore {

    private final EventStoreRepository eventStoreRepository;
    private final AccountEventProducer accountEventProducer;

    @Override
    public void saveEvents(UUID aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        List<EventModel> eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (expectedVersion != -1 && eventStream.get(eventStream.size() - 1).getVersion() != expectedVersion) {
            throw new ConcurrencyFailureException("Concurrency problem with aggregate " + aggregateId);
        }
        int version = expectedVersion;
        for (BaseEvent event : events) {
            version++;
            event.setVersion(version);

            EventModel eventModel = EventModel.builder()
                    .id(event.getId())
                    .timestamp(new Date())
                    .aggregateIdentifier(aggregateId)
                    .aggregateType(AccountAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .event(event)
                    .build();

            EventModel persistedModel = eventStoreRepository.save(eventModel);
            if (persistedModel.getId() != null) {
                accountEventProducer.produce(event.getClass().getSimpleName(), event);
            }
        }
    }

    @Override
    public List<BaseEvent> getById(UUID aggregateId) {
        List<EventModel> eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventStream == null) {
            throw new RuntimeException("Aggregate " + aggregateId + " not found");
        }
        return eventStream.stream().map(EventModel::getEvent).collect(Collectors.toList());
    }
}
