package com.ls.wod.resource.util;

import java.util.Map;

/**
 *
 * @author francisco
 */
public interface DeleteResource<T extends Object, ID extends Object> {
    
    public Map<String, String> delete(ID id);
    
}
