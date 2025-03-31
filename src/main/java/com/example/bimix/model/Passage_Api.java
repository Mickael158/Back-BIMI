package com.example.bimix.model;

import java.util.List;

public class Passage_Api {
    private Depart depart;
    private Itineraire itineraires;
    private Soa_personne soa_personne;
    private String code_visa_passage;

    public Passage_Api(Depart depart, Itineraire itineraires, Soa_personne soa_personne, String code_visa_passage) {
        this.depart = depart;
        this.itineraires = itineraires;
        this.soa_personne = soa_personne;
        this.code_visa_passage = code_visa_passage;
    }

    public Soa_personne getSoa_personne() {
        return soa_personne;
    }

    public void setSoa_personne(Soa_personne soa_personne) {
        this.soa_personne = soa_personne;
    }

    public Depart getDepart() {
        return depart;
    }

    public void setDepart(Depart depart) {
        this.depart = depart;
    }

    public Itineraire getItineraires() {
        return itineraires;
    }

    public void setItineraires(Itineraire itineraires) {
        this.itineraires = itineraires;
    }

    public String getCode_visa_passage() {
        return code_visa_passage;
    }

    public void setCode_visa_passage(String code_visa_passage) {
        this.code_visa_passage = code_visa_passage;
    }
}
