package org.wsd.app.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wsd.core.aggregate.AggregateRoot;
import org.wsd.app.aggregate.AccountAggregate;
import org.wsd.core.events.BaseEvent;
import org.wsd.core.eventstore.EventStore;
import org.wsd.core.handler.EventSourcingHandler;

import java.util.List;
import java.util.UUID;

@Service
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {

    @Autowired
    private EventStore eventStore;


    @Override
    public void save(AggregateRoot aggregateRoot) {
        eventStore.saveEvents(aggregateRoot.getId(), aggregateRoot.getUncommitedChanges(), aggregateRoot.getVersion());
        aggregateRoot.markChangesAsCommitted();
    }

    @Override
    public AccountAggregate getById(UUID aggregateRootId) {
        AccountAggregate accountAggregate = new AccountAggregate();
        List<BaseEvent> events = eventStore.getById(aggregateRootId);
        if (events != null && !events.isEmpty()) {
            accountAggregate.replayEvents(events);
            int latestVersion = events.stream().map(BaseEvent::getVersion).max(Integer::compareTo).get();
            accountAggregate.setVersion(latestVersion);
        }
        return accountAggregate;
    }
}
