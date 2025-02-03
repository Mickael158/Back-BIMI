package com.example.bimi.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUtilisateur", nullable = false)
    private Integer idUtilisateur;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "pwd")
    private String pwd;

    @ManyToOne
    @JoinColumn(name = "idPersonnel")
    private Personnel idPersonnel;

    @ManyToOne
    @JoinColumn(name = "idRole")
    private Personnel idRole;

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Personnel getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(Personnel idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public Personnel getIdRole() {
        return idRole;
    }

    public void setIdRole(Personnel idRole) {
        this.idRole = idRole;
    }
}
