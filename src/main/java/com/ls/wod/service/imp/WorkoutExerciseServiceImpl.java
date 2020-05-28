package com.ls.wod.service.imp;

import com.ls.wod.domain.WorkoutExercise;
import com.ls.wod.domain.Workout;
import com.ls.wod.exception.GenericException;
import com.ls.wod.repository.WorkoutExerciseRepository;
import com.ls.wod.service.WorkoutExerciseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */

@Service("WorkoutExerciseService")
public class WorkoutExerciseServiceImpl implements WorkoutExerciseService {

    @Autowired
    WorkoutExerciseRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<WorkoutExercise> findByWorkout(Workout workout) {
        return repository.findByWorkout(workout);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkoutExercise> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public WorkoutExercise findById(WorkoutExercise t) {
        return repository.findById(t.getIdWorkoutExercise()).orElseThrow(null);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<WorkoutExercise> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(WorkoutExercise t) {
        return repository.existsById(t.getIdWorkoutExercise());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public WorkoutExercise save(WorkoutExercise t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<WorkoutExercise> saveAll(Iterable<WorkoutExercise> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(WorkoutExercise t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        try {
            repository.deleteAll();
        } catch (Exception e) {
            throw new GenericException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<WorkoutExercise> itrbl) {
        repository.deleteAll(itrbl);
    }
}
