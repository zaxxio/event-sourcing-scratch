package org.wsd.app.domain;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.experimental.Tolerate;
import org.wsd.core.domain.BaseEntity;

import java.util.UUID;

@Data
@Entity
@Builder
public class BankAccount extends BaseEntity {
    @Id
    private UUID id;
    private String accountHolder;
    private String accountType;
    private Double balance;
    private Boolean active;

    @Tolerate
    public BankAccount() {

    }
}
