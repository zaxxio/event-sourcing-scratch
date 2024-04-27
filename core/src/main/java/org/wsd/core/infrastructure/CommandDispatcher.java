package org.wsd.core.infrastructure;

import org.wsd.core.commands.BaseCommand;
import org.wsd.core.commands.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> command);

    void send(BaseCommand command) throws Exception;
}
