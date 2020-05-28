package com.ls.wod.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author francisco
 */

@Entity
@Table(name = "workout_traininglevel")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WorkoutTraininglevel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idWorkoutTraininglevel")
    private Integer idWorkoutTraininglevel;
    
    @JoinColumn(name = "idTrainingLevel", referencedColumnName = "idTrainingLevel")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Traininglevel traininglevel;
    
    @JoinColumn(name = "idWorkout", referencedColumnName = "idWorkout")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Workout workout;

    public WorkoutTraininglevel() {
    }

    public WorkoutTraininglevel(Integer idWorkoutTraininglevel) {
        this.idWorkoutTraininglevel = idWorkoutTraininglevel;
    }
}
