package org.wsd.app.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.wsd.core.aggregate.AggregateRoot;
import org.wsd.app.commands.CreateAccountCommand;
import org.wsd.app.events.AccountCreatedEvent;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountAggregate extends AggregateRoot {

    private Boolean active;
    private Double balance;

    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        raiseEvent(
                AccountCreatedEvent.builder()
                        .id(createAccountCommand.getId())
                        .accountHolder(createAccountCommand.getAccountHolder())
                        .accountType(createAccountCommand.getAccountType())
                        .active(true)
                        .balance(createAccountCommand.getBalance())
                        .build()
        );
    }

    public void on(AccountCreatedEvent event) {
        this.id = event.getId();
        this.active = event.getActive();
        this.balance = event.getBalance();
    }

}
