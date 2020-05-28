package com.ls.wod.service.imp;

import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.ls.wod.repository.TypeexerciseRepository;
import com.ls.wod.domain.Typeexercise;
import com.ls.wod.exception.NotFoundException;
import com.ls.wod.service.TypeexerciseService;
import java.util.List;

/**
 *
 * @author francisco
 */

@Service("TypeexerciseService")
public class TypeexerciseServiceImpl implements TypeexerciseService {

    @Autowired
    private TypeexerciseRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Typeexercise> findAll() {
        return (List<Typeexercise>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Typeexercise findById(Typeexercise t) {
        return repository.findById(t.getIdTypeExercise()).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Typeexercise> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Typeexercise t) {
        return repository.existsById(t.getIdTypeExercise());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Typeexercise save(Typeexercise t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<Typeexercise> saveAll(Iterable<Typeexercise> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(Typeexercise t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Typeexercise> itrbl) {
        repository.deleteAll(itrbl);
    }
}
