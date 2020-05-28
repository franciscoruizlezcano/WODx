package com.ls.wod.service.imp;

import com.ls.wod.domain.Company;
import com.ls.wod.domain.Person;
import com.ls.wod.domain.Typeuser;
import com.ls.wod.domain.User;
import com.ls.wod.exception.NotFoundException;
import com.ls.wod.repository.UserRepository;
import com.ls.wod.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    @Override
    public User findByPerson(Person person) {
        return repository.findByPerson(person);
    }

    @Override
    public Iterable<User> findByTypeUser(Typeuser typeUser) {
        return repository.findByTypeUser(typeUser);
    }

    @Override
    public Iterable<User> findByCompany(Company company) {
        return repository.findByCompany(company);
    }

    @Override
    public Iterable<User> findByCompanyAndTypeUser(Company company, Typeuser typeUser) {
        return repository.findByCompanyAndTypeUser(company, typeUser);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<User> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(User t) {
        return repository.findById(t.getIdUser()).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<User> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(User t) {
        return repository.existsById(t.getIdUser());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public User save(User t) {
        String passBackup;
        passBackup = encoder.encode(t.getPassword());
        t.setPassword(passBackup);
        return repository.save(t);
    }

    @Override
    @Transactional
    public User save(User t, boolean crypt) {
        if (crypt) {
            String passBackup;
            passBackup = encoder.encode(t.getPassword());
            t.setPassword(passBackup);
        }
        return repository.save(t);
    }

    @Override
    @Transactional
    public Iterable<User> saveAll(Iterable<User> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(User t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<User> itrbl) {
        repository.deleteAll(itrbl);

    }

}
