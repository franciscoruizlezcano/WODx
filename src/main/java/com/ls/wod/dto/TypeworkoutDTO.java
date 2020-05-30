package com.ls.wod.dto;

import lombok.Data;

/**
 *
 * @author francisco
 */

@Data
public class TypeworkoutDTO {

    private Integer idTypeWorkout;

    private String description;

    public TypeworkoutDTO() {
    }

    public TypeworkoutDTO(Integer idTypeWorkout) {
        this.idTypeWorkout = idTypeWorkout;
    }

}