package org.wsd.commands;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.wsd.messages.Message;

@Data
@NoArgsConstructor
public abstract class BaseCommand extends Message {
    public BaseCommand(String id) {
        super(id);
    }
}
