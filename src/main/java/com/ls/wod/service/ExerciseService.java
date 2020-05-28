package com.ls.wod.service;

import com.ls.wod.domain.Exercise;
import com.ls.wod.domain.Typeexercise;
import java.util.List;

/**
 *
 * @author francisco
 */

public interface ExerciseService extends CrudService<Exercise>{
    
    List<Exercise> findByDescription(String description);
    
    List<Exercise> findByTypeExercise(Typeexercise typeExercise);
}
