package com.ls.wod.service;

import com.ls.wod.domain.Coach;
import com.ls.wod.domain.Company;
import com.ls.wod.domain.User;

/**
 *
 * @author francisco
 */

public interface CoachService extends CrudService<Coach>{
    Coach findByUser(User user);
    
    Iterable<Coach> findByCompany(Company company);

    long countByCompany(Company company);
    
    Coach save(Coach t, boolean crypt);
}
