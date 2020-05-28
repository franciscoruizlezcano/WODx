package com.ls.wod.resource.util;

/**
 *
 * @author francisco
 * @param <T>
 * @param <ID>
 */

public interface CreateUpdateResource<T extends Object, ID extends Object> {
    public T save(T t);
    
    public T update(T t, ID id);
}
