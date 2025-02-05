package com.example.bimix.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "inscription")
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInscription", nullable = false)
    private Integer idInscription;

    @Column(name = "matricule")
    private String matricule;
    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "CIN")
    private String CIN;

    @Column(name = "CIN_du")
    private Date CIN_du;

    @Column(name = "indice")
    private String indice;

    @Column(name = "CatOR")
    private String CatOR;

    @Column(name = "codeGrade")
    private String codeGrade;

    @Column(name = "email")
    private String email;

    @Column(name = "tel")
    private String tel;

    @Column(name = "cles")
    private String cles;

    @Column(name = "pwd")
    private String pwd;

    @ManyToOne
    @JoinColumn(name = "idFonction")
    private Fonction idFonction;

    @ManyToOne
    @JoinColumn(name = "idService")
    private ServiceM idService;

    @ManyToOne
    @JoinColumn(name = "IdCatOr")
    private CatOR IdCatOr;

    public com.example.bimix.model.CatOR getIdCatOr() {
        return IdCatOr;
    }

    public void setIdCatOr(com.example.bimix.model.CatOR idCatOr) {
        IdCatOr = idCatOr;
    }

    public Integer getIdInscription() {
        return idInscription;
    }

    public void setIdInscription(Integer idInscription) {
        this.idInscription = idInscription;
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

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
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

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }

    public String getCatOR() {
        return CatOR;
    }

    public void setCatOR(String catOR) {
        CatOR = catOR;
    }

    public String getCodeGrade() {
        return codeGrade;
    }

    public void setCodeGrade(String codeGrade) {
        this.codeGrade = codeGrade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCles() {
        return cles;
    }

    public void setCles(String cles) {
        this.cles = cles;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Fonction getIdFonction() {
        return idFonction;
    }

    public void setIdFonction(Fonction idFonction) {
        this.idFonction = idFonction;
    }

    public ServiceM getIdService() {
        return idService;
    }

    public void setIdService(ServiceM idService) {
        this.idService = idService;
    }

}
