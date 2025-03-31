package com.example.bimix.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fonction")
public class Fonction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfonction", nullable = false)
    private Integer idFonction;

    @Column(name = "nom")
    private String nom;

    public Integer getIdFonction() {
        return idFonction;
    }

    public void setIdFonction(Integer idFonction) {
        this.idFonction = idFonction;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
