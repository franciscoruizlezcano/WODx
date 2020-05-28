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
@Table(name = "coach")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Coach implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCoach")
    private Integer idCoach;
    
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private User user;
    
    public Coach() {
    }

    public Coach(Integer idCoach) {
        this.idCoach = idCoach;
    }

}
