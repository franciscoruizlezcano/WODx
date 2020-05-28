package com.ls.wod.service.imp;

import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.ls.wod.repository.PersonRepository;
import com.ls.wod.domain.Person;
import com.ls.wod.domain.Phone;
import com.ls.wod.service.PersonService;
import java.util.List;

/**
 *
 * @author francisco
 */
@Service("PersonService")
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return (List<Person>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Person findById(Person t) {
        return repository.findById(t.getIdPerson()).orElseThrow(null);
    }

    @Override
    public Person findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Person findByPhone(Phone phone) {
        return (Person) repository.findByPhone(phone);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Person> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Person t) {
        return repository.existsById(t.getIdPerson());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Person save(Person t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<Person> saveAll(Iterable<Person> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(Person t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Person> itrbl) {
        repository.deleteAll(itrbl);
    }
}
