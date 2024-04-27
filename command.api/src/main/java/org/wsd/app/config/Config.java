package org.wsd.app.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.wsd.app.commands.*;
import org.wsd.core.infrastructure.CommandDispatcher;

@Configuration
public class Config {

    private final CommandDispatcher commandDispatcher;
    private final CommandHandler commandHandler;

    public Config(CommandDispatcher commandDispatcher,
                  CommandHandler commandHandler) {
        this.commandDispatcher = commandDispatcher;
        this.commandHandler = commandHandler;
    }

    @PostConstruct
    public void registerHandlers() {
        commandDispatcher.registerHandler(CreateAccountCommand.class, commandHandler::handle);
        commandDispatcher.registerHandler(WithdrawCashCommand.class, commandHandler::handle);
        commandDispatcher.registerHandler(DepositCashCommand.class, commandHandler::handle);
        commandDispatcher.registerHandler(CloseAccountCommand.class, commandHandler::handle);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
            }
        };
    }


}
