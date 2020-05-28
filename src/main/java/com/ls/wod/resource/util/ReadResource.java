package com.ls.wod.resource.util;

import java.util.Map;

/**
 *
 * @author francisco
 */
public interface ReadResource<T extends Object, ID extends Object> {

    public T findById(ID id);

    public Iterable findAll();

    public Map<String, Long> count();
}
