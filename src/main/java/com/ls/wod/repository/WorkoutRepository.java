package com.ls.wod.repository;

import com.ls.wod.domain.Company;
import com.ls.wod.domain.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author francisco
 */

public interface WorkoutRepository extends JpaRepository<Workout, Integer>{
    @Query("select w from Workout w where w.coach.user.company = :company")
    Iterable<Workout> findByCompany(@Param("company") Company company);
}
