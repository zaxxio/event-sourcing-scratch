package org.wsd.app.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wsd.app.domain.BankAccount;
import org.wsd.app.events.AccountClosedEvent;
import org.wsd.app.events.AccountCreatedEvent;
import org.wsd.app.events.CashDepositedEvent;
import org.wsd.app.events.CashWithdrawnEvent;
import org.wsd.app.handler.EventHandler;
import org.wsd.app.repository.BankAccountRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountEventHandler implements EventHandler {

    private final BankAccountRepository bankAccountRepository;

    @Override
    public void on(AccountCreatedEvent accountCreatedEvent) {
        BankAccount bankAccount = BankAccount.builder()
                .id(accountCreatedEvent.getId())
                .accountHolder(accountCreatedEvent.getAccountHolder())
                .accountType(accountCreatedEvent.getAccountType())
                .active(true)
                .balance(accountCreatedEvent.getBalance())
                .build();

        this.bankAccountRepository.save(bankAccount);

    }

    @Override
    public void on(CashDepositedEvent cashDepositedEvent) {
        Optional<BankAccount> account =
                bankAccountRepository.findById(cashDepositedEvent.getId());
        if (account.isEmpty()) {
            return;
        }
        BankAccount bankAccount = account.get();
        Double balance = bankAccount.getBalance();
        balance += cashDepositedEvent.getAmount();
        bankAccount.setBalance(balance);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void on(CashWithdrawnEvent cashWithdrawnEvent) {
        Optional<BankAccount> account =
                bankAccountRepository.findById(cashWithdrawnEvent.getId());
        if (account.isEmpty()) {
            return;
        }
        BankAccount bankAccount = account.get();
        Double balance = bankAccount.getBalance();
        balance -= cashWithdrawnEvent.getAmount();
        bankAccount.setBalance(balance);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void on(AccountClosedEvent accountClosedEvent) {
        bankAccountRepository.deleteById(accountClosedEvent.getId());
    }
}
