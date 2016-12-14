package net.evdut.cqrs.framework.api;

import java.io.Serializable;

public abstract class GenericQuery<T extends Serializable> implements Query<T> {

	private T result;
	
    private Context context = new Context();

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
