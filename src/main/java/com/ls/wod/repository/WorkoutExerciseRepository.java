package com.ls.wod.repository;

import com.ls.wod.domain.Workout;
import com.ls.wod.domain.WorkoutExercise;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author francisco
 */

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Integer>{
    @Query("select w from WorkoutExercise w where w.workout = :workout")
    List<WorkoutExercise> findByWorkout(@Param("workout") Workout workout);
}
