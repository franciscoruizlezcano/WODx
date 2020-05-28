package com.ls.wod.repository;

import com.ls.wod.domain.Coach;
import com.ls.wod.domain.Company;
import com.ls.wod.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author francisco
 */

public interface CoachRepository extends JpaRepository<Coach, Integer>{
    @Query("select c from Coach c where c.user = :user")
    Coach findByUser(@Param("user") User user);
    
    @Query("select c from Coach c where c.user.company = :company")
    Iterable<Coach> findByCompany(@Param("company") Company company);
}
