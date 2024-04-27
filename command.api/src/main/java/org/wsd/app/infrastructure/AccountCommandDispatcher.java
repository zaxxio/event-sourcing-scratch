package org.wsd.app.infrastructure;

import org.springframework.stereotype.Service;
import org.wsd.core.commands.BaseCommand;
import org.wsd.core.commands.CommandHandlerMethod;
import org.wsd.core.infrastructure.CommandDispatcher;

import java.util.*;

@Service
public class AccountCommandDispatcher implements CommandDispatcher {

    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> command) {
        routes.computeIfAbsent(type, k -> new LinkedList<>()).add(command);
    }

    @Override
    public void send(BaseCommand command) throws Exception {
        List<CommandHandlerMethod> handlers = this.routes.get(command.getClass());
        if (handlers == null || handlers.isEmpty()) {
            throw new Exception("No command handler was registered");
        }
        if (handlers.size() > 1) {
            throw new Exception("More than one command handler was registered");
        }
        handlers.get(0).handle(command);
    }

}
