package com.ls.wod.service.imp;

import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.ls.wod.repository.ExercisemodeRepository;
import com.ls.wod.domain.Exercisemode;
import com.ls.wod.exception.NotFoundException;
import com.ls.wod.service.ExercisemodeService;
import java.util.List;

/**
 *
 * @author francisco
 */
@Service("ExercisemodeService")
public class ExercisemodeServiceImpl implements ExercisemodeService {

    @Autowired
    private ExercisemodeRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Exercisemode> findAll() {
        return (List<Exercisemode>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Exercisemode findById(Exercisemode t) {
        return repository.findById(t.getIdExerciseMode()).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Exercisemode> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Exercisemode t) {
        return repository.existsById(t.getIdExerciseMode());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Exercisemode save(Exercisemode t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<Exercisemode> saveAll(Iterable<Exercisemode> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(Exercisemode t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Exercisemode> itrbl) {
        repository.deleteAll(itrbl);
    }
}
