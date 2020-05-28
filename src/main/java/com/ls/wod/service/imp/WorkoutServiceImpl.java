package com.ls.wod.service.imp;

import com.ls.wod.domain.Company;
import com.ls.wod.domain.Workout;
import com.ls.wod.exception.NotFoundException;
import com.ls.wod.repository.WorkoutRepository;
import com.ls.wod.service.WorkoutService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Service("WorkoutService")
public class WorkoutServiceImpl implements WorkoutService {

    @Autowired
    WorkoutRepository repository;
    
    @Override
    @Transactional(readOnly = true)
    public Iterable<Workout> findByCompany(Company company) {
        return repository.findByCompany(company);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Workout> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Workout findById(Workout t) {
        return repository.findById(t.getIdWorkout()).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Workout> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Workout t) {
        return repository.existsById(t.getIdWorkout());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Workout save(Workout t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<Workout> saveAll(Iterable<Workout> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(Workout t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Workout> itrbl) {
        repository.deleteAll(itrbl);
    }
}
