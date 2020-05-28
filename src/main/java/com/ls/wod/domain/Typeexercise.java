package com.ls.wod.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author francisco
 */

@Entity
@Table(name = "typeexercise")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Typeexercise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTypeExercise")
    private Integer idTypeExercise;
    
    @Basic(optional = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    @Column(name = "description")
    private String description;
    
    public Typeexercise() {
    }

    public Typeexercise(Integer idTypeExercise) {
        this.idTypeExercise = idTypeExercise;
    }
}
