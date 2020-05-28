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
@Table(name = "workout_athlete")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WorkoutAthlete implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idWorkoutAthlete")
    private Integer idWorkoutAthlete;
    
    @JoinColumn(name = "idAthlete", referencedColumnName = "idAthlete")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Athlete athlete;
    
    @JoinColumn(name = "idWorkout", referencedColumnName = "idWorkout")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Workout workout;

    public WorkoutAthlete() {
    }

    public WorkoutAthlete(Integer idWorkoutAthlete) {
        this.idWorkoutAthlete = idWorkoutAthlete;
    }
}
