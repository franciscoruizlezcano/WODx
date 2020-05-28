package com.ls.wod.service.imp;

import com.ls.wod.domain.Traininglevel;
import com.ls.wod.domain.Workout;
import com.ls.wod.domain.WorkoutTraininglevel;
import com.ls.wod.exception.NotFoundException;
import com.ls.wod.repository.WorkoutTraininglevelRepository;
import com.ls.wod.service.WorkoutTraininglevelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */

@Service("WorkoutTraininglevelService")
public class WorkoutTraininglevelServiceImpl implements WorkoutTraininglevelService {

    @Autowired
    WorkoutTraininglevelRepository repository;

    @Override
    public List<WorkoutTraininglevel> findByWorkout(Workout workout) {
        return repository.findByWorkout(workout);
    }

    @Override
    public List<WorkoutTraininglevel> findByTraininglevel(Traininglevel trainingLevel) {
        return repository.findByTraininglevel(trainingLevel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkoutTraininglevel> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public WorkoutTraininglevel findById(WorkoutTraininglevel t) {
        return repository.findById(t.getIdWorkoutTraininglevel()).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<WorkoutTraininglevel> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(WorkoutTraininglevel t) {
        return repository.existsById(t.getIdWorkoutTraininglevel());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public WorkoutTraininglevel save(WorkoutTraininglevel t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<WorkoutTraininglevel> saveAll(Iterable<WorkoutTraininglevel> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(WorkoutTraininglevel t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<WorkoutTraininglevel> itrbl) {
        repository.deleteAll(itrbl);
    }
}
