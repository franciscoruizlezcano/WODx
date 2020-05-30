package com.ls.wod.repository;

import com.ls.wod.domain.Athlete;
import com.ls.wod.domain.Workout;
import com.ls.wod.domain.WorkoutAthlete;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author francisco
 */

public interface WorkoutAthleteRepository extends JpaRepository<WorkoutAthlete, Integer>{
    
    @Query("select w from WorkoutAthlete w where w.athlete = :athlete")
    List<WorkoutAthlete> findByAthlete(@Param("athlete") Athlete athlete);

    @Query("select w from WorkoutAthlete w where w.athlete = :athlete and w.workout.endingdate between w.workout.date and :endingdate")
    List<WorkoutAthlete> findByAthleteAndDay(@Param("athlete") Athlete athlete, @Param("endingdate") Date endingdate);
    
    @Query("select w from WorkoutAthlete w where w.workout = :workout")
    List<WorkoutAthlete> findByWorkout(@Param("workout") Workout workout);
    
}
