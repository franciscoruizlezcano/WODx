package com.ls.wod.service.imp;

import com.ls.wod.domain.Athlete;
import com.ls.wod.domain.Company;
import com.ls.wod.domain.User;
import com.ls.wod.exception.NotFoundException;
import com.ls.wod.repository.AthleteRepository;
import com.ls.wod.service.AthleteService;
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
@Service("AthleteService")
public class AthleteServiceImpl implements AthleteService {

    @Autowired
    AthleteRepository repository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    @Transactional(readOnly = true)
    public Athlete findByUser(User user) {
        return repository.findByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Athlete> findByCompany(Company company) {
        return repository.findByCompany(company);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Athlete> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Athlete findById(Athlete t) {
        return repository.findById(t.getIdAthlete()).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Athlete> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Athlete t) {
        return repository.existsById(t.getIdAthlete());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Athlete save(Athlete t) {
        User user = t.getUser();
        user.setPassword(encoder.encode(t.getUser().getPassword()));
        t.setUser(user);
        return repository.save(t);
    }

    @Override
    @Transactional
    public Athlete save(Athlete t, boolean crypt) {
        if (crypt) {
            User user = t.getUser();
            user.setPassword(encoder.encode(t.getUser().getPassword()));
            t.setUser(user);
        }
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<Athlete> saveAll(Iterable<Athlete> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(Athlete t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Athlete> itrbl) {
        repository.deleteAll(itrbl);
    }

}
