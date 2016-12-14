package net.evdut.cqrs.framework.api;

public interface Handler<T extends Command> {

    public void handle(T command);

}
