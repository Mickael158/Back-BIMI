package com.example.bimix.model;

import jakarta.persistence.*;

import java.sql.Date;

public class Personne_import {

    @Id
    @Column(name = "IM") // Corresponds to the IM column in your SQL table
    private String matricule;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "CIN")
    private String CIN;

    @Column(name = "date_CIN") // Ensure this matches the column name in the database
    private Date CIN_du;

    // Default constructor (required by JPA)
    public Personne_import() {
    }

    // Constructor with parameters for ease of instantiation
    public Personne_import(String matricule, String nom, String prenom, String CIN, Date CIN_du) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.CIN = CIN;
        this.CIN_du = CIN_du;
    }

    // Getters and Setters
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public Date getCIN_du() {
        return CIN_du;
    }

    public void setCIN_du(Date CIN_du) {
        this.CIN_du = CIN_du;
    }
}
