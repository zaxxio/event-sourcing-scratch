package org.wsd.app.handler;

import org.wsd.app.events.AccountClosedEvent;
import org.wsd.app.events.AccountCreatedEvent;
import org.wsd.app.events.CashDepositedEvent;
import org.wsd.app.events.CashWithdrawnEvent;

public interface EventHandler {
    void on(AccountCreatedEvent accountCreatedEvent);

    void on(CashDepositedEvent cashDepositedEvent);

    void on(CashWithdrawnEvent cashWithdrawnEvent);

    void on(AccountClosedEvent accountClosedEvent);
}
