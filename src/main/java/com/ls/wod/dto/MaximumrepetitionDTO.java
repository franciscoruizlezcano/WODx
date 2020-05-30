package com.ls.wod.dto;

import lombok.Data;

/**
 *
 * @author francisco
 */

@Data
public class MaximumrepetitionDTO {

    private Integer idMaximumRepetition;

    private int value;

    private AthleteDTO athlete;

    private ExerciseDTO exercise;

    private WeightunitDTO weightunit;

    public MaximumrepetitionDTO() {
    }

    public MaximumrepetitionDTO(Integer idMaximumRepetition) {
        this.idMaximumRepetition = idMaximumRepetition;
    }
}
