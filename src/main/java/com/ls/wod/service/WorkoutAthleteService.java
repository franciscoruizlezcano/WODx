package com.ls.wod.service;

import com.ls.wod.domain.Athlete;
import com.ls.wod.domain.Workout;
import com.ls.wod.domain.WorkoutAthlete;
import java.util.List;

/**
 *
 * @author francisco
 */

public interface WorkoutAthleteService extends CrudService<WorkoutAthlete>{
    
    List<WorkoutAthlete> findByAthlete(Athlete athlete);

    List<WorkoutAthlete> findByAthleteAndDay(Athlete athlete);
    
    List<WorkoutAthlete> findByWorkout(Workout workout);
    
}
