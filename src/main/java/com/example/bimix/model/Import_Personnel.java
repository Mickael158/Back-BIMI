package com.example.bimix.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "import_personnel")
public class Import_Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idimport_personnel", nullable = false)
    private Integer idimport_personnel;

    @Column(name = "IM")
    private String IM;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "CIN")
    private String CIN;

    @Column(name = "date_CIN")
    private String date_CIN;

    @Column(name = "indice")
    private String indice;

    @Column(name = "catOr")
    private String catOr;

    @Column(name = "code_grade")
    private String code_grade;

    @Column(name = "fonction")
    private String fonction;

    @Column(name = "tel")
    private String tel;

    @Column(name = "direction")
    private String direction;

    @Column(name = "service")
    private String service;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur utilisateur;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Integer getIdimport_personnel() {
        return idimport_personnel;
    }

    public void setIdimport_personnel(Integer idimport_personnel) {
        this.idimport_personnel = idimport_personnel;
    }

    public String getIM() {
        return IM;
    }

    public void setIM(String IM) {
        this.IM = IM;
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

    public String getDate_CIN() {
        return date_CIN;
    }

    public void setDate_CIN(String date_CIN) {
        this.date_CIN = date_CIN;
    }

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }

    public String getCatOr() {
        return catOr;
    }

    public void setCatOr(String catOr) {
        this.catOr = catOr;
    }

    public String getCode_grade() {
        return code_grade;
    }

    public void setCode_grade(String code_grade) {
        this.code_grade = code_grade;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
