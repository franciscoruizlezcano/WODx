package com.ls.wod.service;

import com.ls.wod.domain.Traininglevel;
import com.ls.wod.domain.Workout;
import com.ls.wod.domain.WorkoutTraininglevel;
import java.util.List;

/**
 *
 * @author francisco
 */

public interface WorkoutTraininglevelService extends CrudService<WorkoutTraininglevel>{
    
    List<WorkoutTraininglevel> findByWorkout(Workout workout);
    
    List<WorkoutTraininglevel> findByTraininglevel(Traininglevel trainingLevel);

    List<WorkoutTraininglevel> findByTraininglevelAndDay(Traininglevel trainingLevel);
    
}
