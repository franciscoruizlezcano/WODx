package com.ls.wod.service.imp;

import com.ls.wod.domain.Athlete;
import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.ls.wod.repository.MaximumrepetitionRepository;
import com.ls.wod.domain.Maximumrepetition;
import com.ls.wod.exception.NotFoundException;
import com.ls.wod.service.MaximumrepetitionService;
import java.util.List;

/**
 *
 * @author francisco
 */
@Service("MaximumrepetitionService")
public class MaximumrepetitionServiceImpl implements MaximumrepetitionService {

    @Autowired
    private MaximumrepetitionRepository repository;

    @Override
    public Maximumrepetition finByAthlete(Athlete athlete) {
        return repository.finByAthlete(athlete);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Maximumrepetition> findAll() {
        return (List<Maximumrepetition>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Maximumrepetition findById(Maximumrepetition t) {
        return repository.findById(t.getIdMaximumRepetition()).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Maximumrepetition> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Maximumrepetition t) {
        return repository.existsById(t.getIdMaximumRepetition());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Maximumrepetition save(Maximumrepetition t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<Maximumrepetition> saveAll(Iterable<Maximumrepetition> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(Maximumrepetition t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Maximumrepetition> itrbl) {
        repository.deleteAll(itrbl);
    }
}
