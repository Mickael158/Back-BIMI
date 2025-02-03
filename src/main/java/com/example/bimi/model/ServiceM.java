package com.example.bimi.model;


import jakarta.persistence.*;

@Entity
@Table(name = "service")
public class ServiceM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idService", nullable = false)
    private Integer idService;

    @Column(name = "nom")
    private String nom;

    @ManyToOne
    @JoinColumn(name = "idDirection")
    private Direction idDirection;

    public Integer getIdService() {
        return idService;
    }

    public void setIdService(Integer idService) {
        this.idService = idService;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Direction getIdDirection() {
        return idDirection;
    }

    public void setIdDirection(Direction idDirection) {
        this.idDirection = idDirection;
    }
}
