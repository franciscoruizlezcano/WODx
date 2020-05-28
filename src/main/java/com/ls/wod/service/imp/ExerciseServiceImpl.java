package com.ls.wod.service.imp;

import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.ls.wod.repository.ExerciseRepository;
import com.ls.wod.domain.Exercise;
import com.ls.wod.domain.Typeexercise;
import com.ls.wod.service.ExerciseService;
import java.util.List;

/**
 *
 * @author francisco
 */

@Service("ExerciseService")
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Exercise> findAll() {
        return (List<Exercise>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Exercise findById(Exercise t) {
        return repository.findById(t.getIdExercise()).orElseThrow(null);
    }

    @Override
    public List<Exercise> findByDescription(String description) {
        return repository.findByDescription(description);
    }

    @Override
    public List<Exercise> findByTypeExercise(Typeexercise typeExercise) {
        return repository.findByTypeExercise(typeExercise);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Exercise> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Exercise t) {
        return repository.existsById(t.getIdExercise());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Exercise save(Exercise t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<Exercise> saveAll(Iterable<Exercise> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(Exercise t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Exercise> itrbl) {
        repository.deleteAll(itrbl);
    }
}
