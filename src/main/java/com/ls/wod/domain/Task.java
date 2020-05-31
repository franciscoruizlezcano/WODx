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
@Table(name = "task")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTask")
    private Integer idTask;
    
    @Basic(optional = false)
    @NotNull
    @NotEmpty
    @Column(name = "description")
    private String description;
    
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    public Task() {
    }

    public Task(Integer idTask) {
        this.idTask = idTask;
    }
}
