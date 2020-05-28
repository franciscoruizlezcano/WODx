package com.ls.wod.service.imp;

import com.ls.wod.domain.Country;
import com.ls.wod.repository.CountryRepository;
import com.ls.wod.service.CountryService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Service("CountryService")
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Country> findByDescription(String description) {
        return (List<Country>) repository.findByDescription(description);
    }

    @Override
    @Transactional(readOnly = true)
    public Country findByPhoneCode(String phonecode) {
        return repository.findByPhoneCode(phonecode);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Country> findAll() {
        return (List<Country>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Country findById(Country t) {
        return repository.findById(t.getIdCountry()).orElseThrow(null);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Country> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Country t) {
        return repository.existsById(t.getIdCountry());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Country save(Country t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<Country> saveAll(Iterable<Country> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(Country t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Country> itrbl) {
        repository.deleteAll(itrbl);
    }

}
