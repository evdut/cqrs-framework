package net.evdut.cqrs.framework.command;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class CommandException extends RuntimeException {

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

}
