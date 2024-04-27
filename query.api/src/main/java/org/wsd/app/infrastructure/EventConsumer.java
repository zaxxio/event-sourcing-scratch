package org.wsd.app.infrastructure;

import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.wsd.app.events.AccountClosedEvent;
import org.wsd.app.events.AccountCreatedEvent;
import org.wsd.app.events.CashDepositedEvent;
import org.wsd.app.events.CashWithdrawnEvent;

public interface EventConsumer {
    void consume(@Payload AccountCreatedEvent event, Acknowledgment acknowledgment);

    void consume(@Payload CashDepositedEvent event, Acknowledgment acknowledgment);

    void consume(@Payload CashWithdrawnEvent event, Acknowledgment acknowledgment);

    void consume(@Payload AccountClosedEvent event, Acknowledgment acknowledgment);
}
