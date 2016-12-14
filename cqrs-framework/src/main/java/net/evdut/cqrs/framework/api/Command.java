package net.evdut.cqrs.framework.api;

import java.io.Serializable;

public interface Command<T extends Serializable> extends Serializable {

	public T getResult();

    public void setResult(T result);
	
    public Context getContext();

}
