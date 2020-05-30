package com.ls.wod.dto;


import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 *
 * @author francisco
 */

@Data
public class WorkoutDTO{

    private Integer idWorkout;

    private String description;

    private CoachDTO coach;

    private List<WorkoutExerciseDTO> exerciseList;

    public WorkoutDTO() {
    }

    public WorkoutDTO(Integer idWorkout) {
        this.idWorkout = idWorkout;
    }
}
