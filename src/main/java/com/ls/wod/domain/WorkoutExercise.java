package com.ls.wod.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author francisco
 */
@Entity
@Table(name = "workout_exercise")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WorkoutExercise implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idWorkoutExercise")
    private Integer idWorkoutExercise;
    
    @Basic(optional = false)
    @NotNull
    @NotEmpty
    @Column(name = "value")
    private int value;
    
    @Basic(optional = false)
    @NotNull
    @NotEmpty
    @Column(name = "series")
    private int series;
    
    @JoinColumn(name = "idExercise", referencedColumnName = "idExercise")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Exercise exercise;
    
    @JoinColumn(name = "idExerciseMode", referencedColumnName = "idExerciseMode")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Exercisemode exercisemode;
    
    @JoinColumn(name = "idTimeUnit", referencedColumnName = "idTimeUnit")
    @ManyToOne(fetch = FetchType.LAZY)
    private Timeunit timeunit;
    
    @JoinColumn(name = "idWorkout", referencedColumnName = "idWorkout")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private Workout workout;

    public WorkoutExercise() {
    }

    public WorkoutExercise(Integer idWorkoutExercise) {
        this.idWorkoutExercise = idWorkoutExercise;
    }
}
