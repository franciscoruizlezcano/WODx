package com.ls.wod.repository;

import com.ls.wod.domain.Traininglevel;
import com.ls.wod.domain.Workout;
import com.ls.wod.domain.WorkoutTraininglevel;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author francisco
 */

public interface WorkoutTraininglevelRepository extends JpaRepository<WorkoutTraininglevel, Integer>{
    
    @Query("select w from WorkoutTraininglevel w where w.workout = :workout")
    List<WorkoutTraininglevel> findByWorkout(@Param("workout") Workout workout);
    
    @Query("select w from WorkoutTraininglevel w where w.traininglevel = :traininglevel")
    List<WorkoutTraininglevel> findByTraininglevel(@Param("traininglevel") Traininglevel traininglevel);

    @Query("select w from WorkoutTraininglevel w where w.traininglevel = :traininglevel and w.workout.endingdate between w.workout.date and :endingdate")
    List<WorkoutTraininglevel> findByTraininglevelAndDay(@Param("traininglevel") Traininglevel traininglevel, @Param("endingdate") Date endingdate);
    
}
