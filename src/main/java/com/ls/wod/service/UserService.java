package com.ls.wod.service;

import com.ls.wod.domain.Company;
import com.ls.wod.domain.Person;
import com.ls.wod.domain.Typeuser;
import com.ls.wod.domain.User;

/**
 *
 * @author francisco
 */

public interface UserService extends CrudService<User>{
    
    User findByUsername(String username);
    
    User findByPerson(Person person);
    
    Iterable<User> findByTypeUser(Typeuser typeUser);
    
    Iterable<User> findByCompany(Company company);
    
    Iterable<User> findByCompanyAndTypeUser(Company company, Typeuser typeUser);
    
    User save(User t, boolean crypt);
}
