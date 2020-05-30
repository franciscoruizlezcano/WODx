package com.ls.wod.dto;

import lombok.Data;

/**
 *
 * @author francisco
 */

@Data
public class TypeexerciseDTO{

    private Integer idTypeExercise;

    private String description;
    
    public TypeexerciseDTO() {
    }

    public TypeexerciseDTO(Integer idTypeExercise) {
        this.idTypeExercise = idTypeExercise;
    }
}
