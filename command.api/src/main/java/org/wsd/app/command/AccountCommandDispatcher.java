package org.wsd.app.command;

import org.wsd.commands.BaseCommand;
import org.wsd.commands.CommandHandler;
import org.wsd.infrastructure.CommandDispatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountCommandDispatcher implements CommandDispatcher {

    private final Map<Class<? extends BaseCommand>, List<CommandHandler>> routes = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandler<T> handler) {
        routes.computeIfAbsent(type, c -> new ArrayList<>())
                .add(handler);
    }

    @Override
    public void send(BaseCommand command) {
        List<CommandHandler> commandHandlers = routes.get(command.getClass());
        if (commandHandlers == null || commandHandlers.size() == 0) {
            throw new IllegalArgumentException("No command handler registered for " + command.getClass());
        }
        if (commandHandlers.size() > 1) {
            throw new RuntimeException("Can't send command handler more than one." + command.getClass());
        }
        commandHandlers.get(0).handle(command);
    }
}
