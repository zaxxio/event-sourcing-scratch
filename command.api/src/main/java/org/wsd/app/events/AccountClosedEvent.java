package org.wsd.app.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.wsd.core.events.BaseEvent;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class AccountClosedEvent extends BaseEvent {
}
