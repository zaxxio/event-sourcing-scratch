package org.wsd.app.commands;

public interface CommandHandler {
    void handle(CreateAccountCommand createAccountCommand);

    void handle(WithdrawCashCommand withdrawCashCommand);

    void handle(DepositCashCommand depositCashCommand);

    void handle(CloseAccountCommand closeAccountCommand);
}
