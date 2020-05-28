package com.ls.wod.resource.util;

/**
 *
 * @author francisco
 * @param <T> Object
 * @param <ID> Value of ID
 */

public interface CrudResource<T extends Object, ID extends Object> extends CreateUpdateResource<T, ID>, ReadResource<T, ID>, DeleteResource<T, ID>{}
