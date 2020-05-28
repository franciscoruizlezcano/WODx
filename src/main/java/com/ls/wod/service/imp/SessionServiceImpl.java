package com.ls.wod.service.imp;

import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.ls.wod.repository.SessionRepository;
import com.ls.wod.domain.Session;
import com.ls.wod.domain.User;
import com.ls.wod.exception.NotFoundException;
import com.ls.wod.service.SessionService;
import java.util.List;

/**
 *
 * @author francisco
 */

@Service("SessionService")
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Session> findByStatus() {
        return repository.findByStatus();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Session> findByUser(User user) {
        return repository.findByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Session> findByUserAndStatus(User user) {
        return repository.findByUserAndStatus(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Session> findAll() {
        return (List<Session>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Session findById(Session t) {
        return repository.findById(t.getIdSession()).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Session> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Session t) {
        return repository.existsById(t.getIdSession());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Session save(Session t) {
        return repository.save(t);

    }

    @Override
    @Transactional
    public List<Session> saveAll(Iterable<Session> itrbl) {
        return repository.saveAll(itrbl);

    }

    @Override
    @Transactional
    public void delete(Session t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Session> itrbl) {
        repository.deleteAll(itrbl);
    }
}
