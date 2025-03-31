package com.example.bimix.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "fonction_personnel")
public class Fonction_personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfonction_personnel", nullable = false)
    private Integer idfonction_personnel;

    @Column(name = "dates")
    private Date dates;

    @ManyToOne
    @JoinColumn(name = "idfonction")
    private Fonction idFonction;

    @ManyToOne
    @JoinColumn(name = "idpersonnel")
    private Personnel idPersonnel;

    public Integer getIdfonction_personnel() {
        return idfonction_personnel;
    }

    public void setIdfonction_personnel(Integer idfonction_personnel) {
        this.idfonction_personnel = idfonction_personnel;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public Fonction getIdFonction() {
        return idFonction;
    }

    public void setIdFonction(Fonction idFonction) {
        this.idFonction = idFonction;
    }

    public Personnel getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(Personnel idPersonnel) {
        this.idPersonnel = idPersonnel;
    }
}
