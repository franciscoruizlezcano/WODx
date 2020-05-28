package com.ls.wod.service.imp;

import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.ls.wod.domain.Typeworkout;
import com.ls.wod.exception.NotFoundException;
import com.ls.wod.repository.TypeworkoutRepository;
import com.ls.wod.service.TypeworkoutService;
import java.util.List;

/**
 *
 * @author francisco
 */

@Service("TypeworkoutService")
public class TypeworkoutServiceImpl implements TypeworkoutService {

    @Autowired
    private TypeworkoutRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Typeworkout> findAll() {
        return (List<Typeworkout>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Typeworkout findById(Typeworkout t) {
        return repository.findById(t.getIdTypeWorkout()).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Typeworkout> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Typeworkout t) {
        return repository.existsById(t.getIdTypeWorkout());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Typeworkout save(Typeworkout t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<Typeworkout> saveAll(Iterable<Typeworkout> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(Typeworkout t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Typeworkout> itrbl) {
        repository.deleteAll(itrbl);
    }
}
