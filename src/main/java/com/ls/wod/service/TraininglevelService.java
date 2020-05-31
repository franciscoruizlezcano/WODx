package com.ls.wod.service;

import com.ls.wod.domain.Company;
import com.ls.wod.domain.Traininglevel;
import java.util.List;

/**
 *
 * @author francisco
 */

public interface TraininglevelService extends CrudService<Traininglevel>{
    List<Traininglevel> findByCompany(Company company);

    long countByCompany(Company company);
}
