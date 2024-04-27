package org.wsd.core.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.wsd.core.messages.Message;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public abstract class BaseCommand extends Message {
}
