package com.ls.wod.service.imp;

import com.ls.wod.domain.Company;
import com.ls.wod.repository.CompanyRepository;
import com.ls.wod.service.CompanyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author francisco
 */
@Service("CompanyService")
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Company> findAll() {
        return (List<Company>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Company findById(Company t) {
        return repository.findById(t.getIdCompany()).orElseThrow(null);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Company> t) {
        return repository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Company t) {
        return repository.existsById(t.getIdCompany());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @Override
    @Transactional
    public Company save(Company t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public List<Company> saveAll(Iterable<Company> itrbl) {
        return repository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(Company t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Company> itrbl) {
        repository.deleteAll(itrbl);
    }
}
