package com.ls.wod.service;

import com.ls.wod.domain.Workout;
import com.ls.wod.domain.WorkoutExercise;
import java.util.List;

/**
 *
 * @author francisco
 */

public interface WorkoutExerciseService extends CrudService<WorkoutExercise>{
    
    List<WorkoutExercise> findByWorkout(Workout workout);
    
}
