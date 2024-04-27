package org.wsd.core.aggregate;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.wsd.core.events.BaseEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Log4j2
@Getter
@Setter
public abstract class AggregateRoot {


    protected UUID id;

    protected int version = -1;

    private final List<BaseEvent> changes = new ArrayList<>();

    public List<BaseEvent> getUncommitedChanges() {
        return changes;
    }

    public void markChangesAsCommitted() {
        changes.clear();
    }

    protected void applyChanges(BaseEvent event, Boolean isNewEvent) throws RuntimeException {
        try {
            Method method = getClass().getDeclaredMethod("on", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        } catch (NoSuchMethodException e) {
            log.error("No such method was found in the aggregate for {0}", event.getClass().getName());
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            if (isNewEvent) {
                changes.add(event);
            }
        }
    }

    public void raiseEvent(BaseEvent event) {
        applyChanges(event, true);
    }

    public void replayEvents(Iterable<BaseEvent> events) {
        events.forEach(event -> applyChanges(event, false));
    }
}
