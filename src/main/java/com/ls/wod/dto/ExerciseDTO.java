package com.ls.wod.dto;

import lombok.Data;

/**
 *
 * @author francisco
 */

@Data
public class ExerciseDTO{

    private Integer idExercise;

    private String description;

    private String ubication;

    private TypeexerciseDTO typeexercise;
    
    public ExerciseDTO() {}

    public ExerciseDTO(Integer idExercise) {
        this.idExercise = idExercise;
    }
}
