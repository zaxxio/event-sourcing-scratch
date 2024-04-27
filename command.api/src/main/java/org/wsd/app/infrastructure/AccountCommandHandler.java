package org.wsd.app.infrastructure;

import org.springframework.stereotype.Service;
import org.wsd.app.aggregate.AccountAggregate;
import org.wsd.app.commands.*;
import org.wsd.core.handler.EventSourcingHandler;

@Service
public class AccountCommandHandler implements CommandHandler {

    private final EventSourcingHandler<AccountAggregate> eventSourcingHandler;

    public AccountCommandHandler(EventSourcingHandler<AccountAggregate> eventSourcingHandler) {
        this.eventSourcingHandler = eventSourcingHandler;
    }

    @Override
    public void handle(CreateAccountCommand createAccountCommand) {
        AccountAggregate accountAggregate = new AccountAggregate(createAccountCommand);
        eventSourcingHandler.save(accountAggregate);
    }

    @Override
    public void handle(WithdrawCashCommand withdrawCashCommand) {

    }

    @Override
    public void handle(DepositCashCommand depositCashCommand) {

    }

    @Override
    public void handle(CloseAccountCommand closeAccountCommand) {

    }
}
