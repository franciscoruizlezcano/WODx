package com.ls.wod.service.imp;

import com.ls.wod.domain.Athlete;
import com.ls.wod.domain.WorkoutAthlete;
import com.ls.wod.domain.Workout;
import com.ls.wod.exception.NotFoundException;
import com.ls.wod.repository.WorkoutAthleteRepository;
import com.ls.wod.service.WorkoutAthleteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Service("WorkoutAthleteService")
public class WorkoutAthleteServiceImpl implements WorkoutAthleteService {

    @Autowired
    WorkoutAthleteRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<WorkoutAthlete> findByAthlete(Athlete athlete) {
        return repository.findByAthlete(athlete);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkoutAthlete> findByWorkout(Workout workout) {
        return repository.findByWorkout(workout);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkoutAthlete> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public WorkoutAthlete findById(WorkoutAthlete t) {
        return repository.findById(t.getIdWorkoutAthlete()).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<WorkoutAthlete> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(WorkoutAthlete t) {
        return repository.existsById(t.getIdWorkoutAthlete());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public WorkoutAthlete save(WorkoutAthlete t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<WorkoutAthlete> saveAll(Iterable<WorkoutAthlete> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(WorkoutAthlete t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<WorkoutAthlete> itrbl) {
        repository.deleteAll(itrbl);
    }
}
