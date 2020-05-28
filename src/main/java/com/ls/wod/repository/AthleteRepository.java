package com.ls.wod.repository;

import com.ls.wod.domain.Athlete;
import com.ls.wod.domain.Company;
import com.ls.wod.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author francisco
 */

public interface AthleteRepository extends JpaRepository<Athlete, Integer>{
    @Query("select a from Athlete a where a.user = :user")
    Athlete findByUser(@Param("user") User user);
    
    @Query("select a from Athlete a where a.user.company = :company")
    Iterable<Athlete> findByCompany(@Param("company") Company company);
}
