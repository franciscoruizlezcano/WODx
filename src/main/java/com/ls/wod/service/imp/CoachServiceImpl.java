package com.ls.wod.service.imp;

import com.ls.wod.domain.Coach;
import com.ls.wod.domain.Company;
import com.ls.wod.domain.User;
import com.ls.wod.exception.NotFoundException;
import com.ls.wod.repository.CoachRepository;
import com.ls.wod.service.CoachService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Service("CoachService")
public class CoachServiceImpl implements CoachService {

    @Autowired
    CoachRepository repository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    @Transactional(readOnly = true)
    public Coach findByUser(User user) {
        return repository.findByUser(user);

    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Coach> findByCompany(Company company) {
        return repository.findByCompany(company);
    }

    @Override
    @Transactional(readOnly = true)
    public long countByCompany(Company company) {
        return repository.countByCompany(company);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Coach> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Coach findById(Coach t) {
        return repository.findById(t.getIdCoach()).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Coach> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Coach t) {
        return repository.existsById(t.getIdCoach());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Coach save(Coach t) {
        User user = t.getUser();
        user.setPassword(encoder.encode(t.getUser().getPassword()));
        t.setUser(user);
        return repository.save(t);
    }

    @Override
    @Transactional
    public Coach save(Coach t, boolean crypt) {
        if (crypt) {
            User user = t.getUser();
            user.setPassword(encoder.encode(t.getUser().getPassword()));
            t.setUser(user);
        }
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<Coach> saveAll(Iterable<Coach> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(Coach t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Coach> itrbl) {
        repository.deleteAll(itrbl);
    }

}
