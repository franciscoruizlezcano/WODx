package com.ls.wod.service;

import com.ls.wod.domain.Person;
import com.ls.wod.domain.Phone;
import java.util.List;

/**
 *
 * @author francisco
 */

public interface PersonService extends CrudService<Person>{
    
    Person findByEmail(String email);
    
    Person findByPhone(Phone phone);
    
}
