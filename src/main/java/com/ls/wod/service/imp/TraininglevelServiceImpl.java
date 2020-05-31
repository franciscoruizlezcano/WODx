package com.ls.wod.service.imp;

import com.ls.wod.domain.Company;
import com.ls.wod.domain.Traininglevel;
import com.ls.wod.exception.NotFoundException;
import com.ls.wod.repository.TraininglevelRepository;
import com.ls.wod.service.TraininglevelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */

@Service("TraininglevelService")
public class TraininglevelServiceImpl implements TraininglevelService {

    @Autowired
    TraininglevelRepository repository;

    @Override
    public List<Traininglevel> findByCompany(Company company) {
        return (List<Traininglevel>) repository.findByCompany(company);
    }

    @Override
    @Transactional(readOnly = true)
    public long countByCompany(Company company) {
        return repository.countByCompany(company);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Traininglevel> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Traininglevel findById(Traininglevel t) {
        return repository.findById(t.getIdTrainingLevel()).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Traininglevel> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Traininglevel t) {
        return repository.existsById(t.getIdTrainingLevel());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Traininglevel save(Traininglevel t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<Traininglevel> saveAll(Iterable<Traininglevel> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(Traininglevel t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Traininglevel> itrbl) {
        repository.deleteAll(itrbl);
    }
}
