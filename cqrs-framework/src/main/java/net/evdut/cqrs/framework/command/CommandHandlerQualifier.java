package net.evdut.cqrs.framework.command;

import javax.enterprise.util.AnnotationLiteral;

import net.evdut.cqrs.framework.api.Command;

public class CommandHandlerQualifier extends AnnotationLiteral<CommandHandler> implements CommandHandler {

    private Class<? extends Command> command;

    public CommandHandlerQualifier(Class<? extends Command> command) {
        this.command = command;
    }

    @Override
    public Class<? extends Command> command() {
        return command;
    }

    @Override
    public boolean enabled() {
        return true;
    }

}
