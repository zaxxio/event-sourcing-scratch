package org.wsd.core.commands;

import org.wsd.core.messages.Message;

@FunctionalInterface
public interface CommandHandlerMethod<T extends Message> {
    void handle(T command);
}
