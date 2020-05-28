package com.ls.wod.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author francisco
 */

@Entity
@Table(name = "workout")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Workout implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idWorkout")
    private Integer idWorkout;
    
    @Column(name = "description")
    private String description;
    
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;
    
    @JoinColumn(name = "idCoach", referencedColumnName = "idCoach")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Coach coach;

    public Workout() {
    }

    public Workout(Integer idWorkout) {
        this.idWorkout = idWorkout;
    }
}
