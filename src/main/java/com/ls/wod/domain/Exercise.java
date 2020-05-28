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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "exercise")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Exercise implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idExercise")
    private Integer idExercise;
    
    @Basic(optional = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    @Column(name = "description")
    private String description;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "ubication")
    private String ubication;
    
    @JoinColumn(name = "idTypeExercise", referencedColumnName = "idTypeExercise")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Typeexercise typeexercise;
    
    public Exercise() {
    }

    public Exercise(Integer idExercise) {
        this.idExercise = idExercise;
    }
}
