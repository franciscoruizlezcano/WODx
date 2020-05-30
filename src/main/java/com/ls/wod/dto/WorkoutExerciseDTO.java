package com.ls.wod.dto;

import lombok.Data;

/**
 *
 * @author francisco
 */

@Data
public class WorkoutExerciseDTO{

    private int value;

    private int series;

    private ExerciseDTO exercise;

    private ExercisemodeDTO exercisemode;

    private TimeunitDTO timeunit;

    public WorkoutExerciseDTO() {
    }
}
