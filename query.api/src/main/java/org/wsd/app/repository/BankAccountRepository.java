package org.wsd.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wsd.app.domain.BankAccount;

import java.util.UUID;

public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {
}
