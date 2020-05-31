package com.ls.wod.repository;

import com.ls.wod.domain.Company;
import com.ls.wod.domain.Traininglevel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author francisco
 */

public interface TraininglevelRepository extends JpaRepository<Traininglevel, Integer>{
    @Query("select t from Traininglevel t where t.company = :company")
    List<Traininglevel> findByCompany(@Param("company") Company company);

    @Query("select count(t) from Traininglevel t where t.company = :company")
    long countByCompany(@Param("company") Company company);
}
