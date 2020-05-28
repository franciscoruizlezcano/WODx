package com.ls.wod.service.imp;

import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.ls.wod.repository.TypeuserRepository;
import com.ls.wod.domain.Typeuser;
import com.ls.wod.exception.NotFoundException;
import com.ls.wod.service.TypeuserService;
import java.util.List;

/**
 *
 * @author francisco
 */
@Service("TypeuserService")
public class TypeuserServiceImpl implements TypeuserService {

    @Autowired
    private TypeuserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Typeuser> findAll() {
        return (List<Typeuser>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Typeuser findById(Typeuser t) {
        return repository.findById(t.getIdTypeUser()).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Typeuser> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Typeuser t) {
        return repository.existsById(t.getIdTypeUser());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Typeuser save(Typeuser t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<Typeuser> saveAll(Iterable<Typeuser> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(Typeuser t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Typeuser> itrbl) {
        repository.deleteAll(itrbl);
    }
}
