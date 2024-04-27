package org.wsd.app.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wsd.app.commands.CreateAccountCommand;
import org.wsd.core.infrastructure.CommandDispatcher;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
@Tag(value = "Account-Command-Controller")
public class AccountCommandController {

    @Autowired
    private CommandDispatcher commandDispatcher;

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountCommand command) throws Exception {
        UUID uuid = UUID.randomUUID();
        CreateAccountCommand createAccountCommand = CreateAccountCommand.builder()
                .id(uuid)
                .accountHolder(command.getAccountHolder())
                .accountType(command.getAccountType())
                .balance(command.getBalance())
                .build();
        commandDispatcher.send(createAccountCommand);
        return ResponseEntity.status(HttpStatus.CREATED).body(uuid.toString());
    }

}
