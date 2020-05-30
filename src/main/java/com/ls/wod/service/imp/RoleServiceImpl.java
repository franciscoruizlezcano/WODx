package com.ls.wod.service.imp;

import com.ls.wod.domain.Role;
import com.ls.wod.exception.NotFoundException;
import com.ls.wod.repository.RoleRepository;
import com.ls.wod.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author francisco
 */
@Service("RoleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Role findById(Role t) {
        return roleRepository.findById(t.getIdRole()).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Example<Role> t) {
        return roleRepository.exists(t);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Role t) {
        return roleRepository.existsById(t.getIdRole());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return roleRepository.count();
    }

    @Override
    @Transactional
    public Role save(Role t) {
        return roleRepository.save(t);
    }

    @Override
    @Transactional
    public List<Role> saveAll(Iterable<Role> itrbl) {
        return roleRepository.saveAll(itrbl);
    }

    @Override
    @Transactional
    public void delete(Role t) {
        roleRepository.delete(t);
    }

    @Override
    @Transactional
    public void deleteAll() {
        roleRepository.deleteAll();
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<Role> itrbl) {
        roleRepository.deleteAll(itrbl);
    }
}
