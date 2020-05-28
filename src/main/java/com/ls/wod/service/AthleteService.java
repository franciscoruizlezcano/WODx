package com.ls.wod.service;

import com.ls.wod.domain.Athlete;
import com.ls.wod.domain.Company;
import com.ls.wod.domain.User;

/**
 *
 * @author francisco
 */

public interface AthleteService extends CrudService<Athlete>{
    Athlete findByUser(User user);
    
    Iterable<Athlete> findByCompany(Company company);
    
    Athlete save(Athlete t, boolean crypt);
}
