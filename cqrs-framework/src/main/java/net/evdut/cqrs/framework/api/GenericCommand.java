package net.evdut.cqrs.framework.api;

import java.io.Serializable;

public abstract class GenericCommand<T extends Serializable> implements Command<T> {

    private final Context context = new Context();
    private T result;
    
    @Override
    public Context getContext() {
        return context;
    }

	@Override
	public T getResult() {
		return result;
	}

	@Override
	public void setResult(T result) {
		this.result = result;
	}
}
