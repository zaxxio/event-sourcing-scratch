package org.wsd.app.commands;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.wsd.core.commands.BaseCommand;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CloseAccountCommand extends BaseCommand {
    @Builder.Default
    private Boolean active = false;
}
