package org.wsd.commands;

@FunctionalInterface
public interface CommandHandler<T extends BaseCommand> {
    void handle(T command);
}
