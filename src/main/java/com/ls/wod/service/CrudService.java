package com.ls.wod.service;

import org.springframework.data.domain.Example;

/**
 *
 * @author francisco
 */

public interface CrudService<T extends Object> {
    public Iterable<T> findAll();

    public T findById(T t);

    public boolean exists(Example<T> t);

    public boolean existsById(T t);

    public long count();

    public T save(T t);
    
    public Iterable<T> saveAll(Iterable<T> itrbl);
    
    public void delete(T t);

    public void deleteAll();

    public void deleteAll(Iterable<T> itrbl);
}
