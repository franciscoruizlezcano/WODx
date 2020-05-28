package com.ls.wod.service;

import com.ls.wod.domain.Company;
import com.ls.wod.domain.Workout;

/**
 *
 * @author francisco
 */

public interface WorkoutService extends CrudService<Workout>{
    Iterable<Workout> findByCompany(Company company);
}
