package org.wsd.app.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.wsd.core.commands.BaseCommand;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountCommand extends BaseCommand {
    private String accountHolder;
    private String accountType;
    private Double balance;
    private Boolean active;
}
