package com.ls.wod.repository;

import com.ls.wod.domain.Athlete;
import com.ls.wod.domain.Maximumrepetition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author francisco
 */

public interface MaximumrepetitionRepository extends JpaRepository<Maximumrepetition, Integer>{
    @Query("select m from Maximumrepetition m where m.athlete = :athlete")
    Maximumrepetition finByAthlete(@Param("athlete") Athlete athlete);
}
