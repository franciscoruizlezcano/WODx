package com.ls.wod.service.imp;

import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.ls.wod.domain.Weightunit;
import com.ls.wod.exception.NotFoundException;
import com.ls.wod.repository.WeightunitRepository;
import com.ls.wod.service.WeightunitService;
import java.util.List;

/**
 *
 * @author francisco
 */
@Service("WeightunitService")
public class WeightunitServiceImpl implements WeightunitService {

    @Autowired
    private WeightunitRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Weightunit> findAll() {
        return (List<Weightunit>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Weightunit findById(Weightunit t) {
        return repository.findById(t.getIdWeightUnit()).orElseThrow(NotFoundException::new);

    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Weightunit> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Weightunit t) {
        return repository.existsById(t.getIdWeightUnit());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Weightunit save(Weightunit t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<Weightunit> saveAll(Iterable<Weightunit> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(Weightunit t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Weightunit> itrbl) {
        repository.deleteAll(itrbl);
    }
}
