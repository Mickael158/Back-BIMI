package com.example.bimix.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "paiement_situation")
public class Paiement_situation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpaiement_situation", nullable = false)
    private Integer idPaiement_situation;

    @Column(name = "dates")
    private Date dates;

    @Column(name = "situation")
    private String situation;

    @ManyToOne
    @JoinColumn(name = "Idpaiement")
    private Paiement Idpaiement;

    @ManyToOne
    @JoinColumn(name = "idutilisateur")
    private Utilisateur IdUtilisateur;

    public Integer getIdPaiement_situation() {
        return idPaiement_situation;
    }

    public void setIdPaiement_situation(Integer idPaiement_situation) {
        this.idPaiement_situation = idPaiement_situation;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }


    public Paiement getIdpaiement() {
        return Idpaiement;
    }

    public void setIdpaiement(Paiement idpaiement) {
        Idpaiement = idpaiement;
    }

    public Utilisateur getIdUtilisateur() {
        return IdUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        IdUtilisateur = idUtilisateur;
    }
}
