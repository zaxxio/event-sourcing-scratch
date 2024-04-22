package org.wsd.infrastructure;


import org.wsd.commands.BaseCommand;
import org.wsd.commands.CommandHandler;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandler<T> handler);

    void send(BaseCommand command);
}
