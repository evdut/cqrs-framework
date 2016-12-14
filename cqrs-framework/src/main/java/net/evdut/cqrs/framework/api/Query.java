package net.evdut.cqrs.framework.api;

import java.io.Serializable;

public interface Query<T extends Serializable> extends Command<T> {

}
