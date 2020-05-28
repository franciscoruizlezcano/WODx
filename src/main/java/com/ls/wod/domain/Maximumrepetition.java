package com.ls.wod.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "maximumrepetition")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Maximumrepetition implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMaximumRepetition")
    private Integer idMaximumRepetition;
    
    @Basic(optional = false)
    @NotNull
    @NotEmpty
    @Column(name = "value")
    private int value;
    
    @JoinColumn(name = "idAthlete", referencedColumnName = "idAthlete")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Athlete athlete;
    
    @JoinColumn(name = "idExercise", referencedColumnName = "idExercise")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Exercise exercise;
    
    @JoinColumn(name = "idWeightUnit", referencedColumnName = "idWeightUnit")
    @ManyToOne(fetch = FetchType.LAZY)
    private Weightunit weightunit;

    public Maximumrepetition() {
    }

    public Maximumrepetition(Integer idMaximumRepetition) {
        this.idMaximumRepetition = idMaximumRepetition;
    }
}
