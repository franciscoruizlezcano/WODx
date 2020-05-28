package com.ls.wod.service.imp;

import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.ls.wod.repository.PhoneRepository;
import com.ls.wod.domain.Phone;
import com.ls.wod.service.PhoneService;
import java.util.List;

/**
 *
 * @author francisco
 */
@Service("PhoneService")
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Phone> findAll() {
        return (List<Phone>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Phone findById(Phone t) {
        return repository.findById(t.getIdPhone()).orElseThrow(null);
    }

    @Override
    public Phone findByPhone(String phone) {
        return repository.findByPhone(phone);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Phone> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Phone t) {
        return repository.existsById(t.getIdPhone());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Phone save(Phone t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<Phone> saveAll(Iterable<Phone> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(Phone t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Phone> itrbl) {
        repository.deleteAll(itrbl);
    }
}
