package com.example.bimix.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transport")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtransport", nullable = false)
    private Integer idtransport;

    @Column(name = "nom")
    private String nom;

    public Integer getIdtransport() {
        return idtransport;
    }

    public void setIdtransport(Integer idtransport) {
        this.idtransport = idtransport;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
