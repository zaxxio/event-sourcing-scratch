package org.wsd.app.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.wsd.core.events.BaseEvent;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CashWithdrawnEvent extends BaseEvent {
    private Double amount;
}
