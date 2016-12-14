package net.evdut.cqrs.framework.command;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Collection;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.UnsatisfiedResolutionException;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import net.evdut.cqrs.framework.Mode;
import net.evdut.cqrs.framework.UnknowCommandException;
import net.evdut.cqrs.framework.api.Command;
import net.evdut.cqrs.framework.api.Handler;
import net.evdut.cqrs.framework.api.Query;

public abstract class CommandInvoker {

    @Any
    @Inject
    private Instance<Handler<? extends Command>> handlers;

    public <T extends Command> void command(@NotNull T... commands) {
        command(Mode.Synchronous, commands);
    }

    public <T extends Command> void command(Mode mode, @NotNull T... commands) {
        for (T command : commands) {
            invoke(command, mode);
        }
    }

    public <T extends Command> void command(@NotNull Collection<T> commands) {
        command(Mode.Synchronous, commands);
    }

    public <T extends Command> void command(Mode mode, @NotNull Collection<T> commands) {
        command(mode, commands.toArray(new Command[commands.size()]));
    }

    public <T extends Query<R>, R extends Serializable> R query(@NotNull T query) {
        return invoke(query, Mode.Synchronous).getResult();
    }
    
    public <T extends Command<R>, R extends Serializable> R command(@NotNull T command) {
        return invoke(command, Mode.Synchronous).getResult();
    }

    private <T extends Command> T invoke(@NotNull T command, Mode mode) {
        Annotation qualifier = new CommandHandlerQualifier(command.getClass());

        Instance<Handler<? extends Command>> instance = null;
        Handler handler = null;
        try {
            instance = handlers.select(qualifier);
            handler = instance.get();

            try {
                if (mode == Mode.Synchronous) {
                    handler.handle(command);
                } else {
                    // TODO
                    System.out.println("Asynchronous not yet implemented");
                }
            } catch (Exception ex) {
                throw new CommandException(ex.getMessage(), ex);
            }

            return command;
        } catch (UnsatisfiedResolutionException ex) {
            // TODO: Add some information to exception
            throw new UnknowCommandException();
        } finally {
            if (instance != null && handler != null) {
                instance.destroy(handler);
            }
        }

    }

}
