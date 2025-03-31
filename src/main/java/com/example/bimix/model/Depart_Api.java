package com.example.bimix.model;

import java.util.List;

public class Depart_Api {
    private Depart depart;
    private List<Itineraire> itineraires;
    private Soa_personne soa_personne;

    public Depart_Api(Depart depart, List<Itineraire> itineraires, Soa_personne soa_personne) {
        this.depart = depart;
        this.itineraires = itineraires;
        this.soa_personne = soa_personne;
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

    public List<Itineraire> getItineraires() {
        return itineraires;
    }

    public void setItineraires(List<Itineraire> itineraires) {
        this.itineraires = itineraires;
    }
}
