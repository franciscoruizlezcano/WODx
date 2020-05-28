package com.ls.wod.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
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
import lombok.Data;

/**
 *
 * @author francisco
 */
@Entity
@Table(name = "athlete")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Athlete implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAthlete")
    private Integer idAthlete;

    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private User user;

    @JoinColumn(name = "idTrainingLevel", referencedColumnName = "idTrainingLevel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Traininglevel traininglevel;

    public Athlete() {
    }

    public Athlete(Integer idAthlete) {
        this.idAthlete = idAthlete;
    }
}
