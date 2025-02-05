package com.example.bimix.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CatOR")
public class CatOR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCatOr", nullable = false)
    private Integer IdCatOr;

    @Column(name = "nom")
    private String nom;
    @Column(name = "codeGrade")
    private String codeGrade;
    @Column(name = "indice")
    private String indice;

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }

    public Integer getIdCatOr() {
        return IdCatOr;
    }

    public void setIdCatOr(Integer idCatOr) {
        IdCatOr = idCatOr;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodeGrade() {
        return codeGrade;
    }

    public void setCodeGrade(String codeGrade) {
        this.codeGrade = codeGrade;
    }
}
