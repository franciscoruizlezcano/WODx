package com.ls.wod.service.imp;

import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.ls.wod.repository.TimeunitRepository;
import com.ls.wod.domain.Timeunit;
import com.ls.wod.exception.NotFoundException;
import com.ls.wod.service.TimeunitService;
import java.util.List;

/**
 *
 * @author francisco
 */
@Service("TimeunitService")
public class TimeunitServiceImpl implements TimeunitService {

    @Autowired
    private TimeunitRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Timeunit> findAll() {
        return (List<Timeunit>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Timeunit findById(Timeunit t) {
        return repository.findById(t.getIdTimeUnit()).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Timeunit> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Timeunit t) {
        return repository.existsById(t.getIdTimeUnit());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Timeunit save(Timeunit t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<Timeunit> saveAll(Iterable<Timeunit> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(Timeunit t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Timeunit> itrbl) {
        repository.deleteAll(itrbl);
    }
}
