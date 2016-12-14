package net.evdut.cqrs.framework.api;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Context implements Serializable {

    private final Map<String, Object> data = new HashMap();

    public Object get(String key) {
        return data.get(key);
    }

    public void put(String key, Object object) {
        this.data.put(key, object);
    }

    public void remove(String key) {
        this.data.remove(key);
    }

    public boolean contains(String key) {
        return data.containsKey(key);
    }

}
