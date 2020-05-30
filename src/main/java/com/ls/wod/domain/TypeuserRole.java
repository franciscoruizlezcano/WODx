package com.ls.wod.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "typeuser_role")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TypeuserRole {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTypeuserRole")
    private Integer idTypeuserRole;

    @JoinColumn(name = "idTypeUser", referencedColumnName = "idTypeUser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Typeuser typeuser;

    @JoinColumn(name = "idRole", referencedColumnName = "idRole")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Role role;

    public TypeuserRole() {
    }

    public TypeuserRole(Integer idTypeuserRole) {
        this.idTypeuserRole = idTypeuserRole;
    }
}
