package com.ls.wod.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author francisco
 */

@Data
public class ExercisemodeDTO{

    private Integer idExerciseMode;

    private String description;

    public ExercisemodeDTO() {}

    public ExercisemodeDTO(Integer idExerciseMode) {
        this.idExerciseMode = idExerciseMode;
    }
}
