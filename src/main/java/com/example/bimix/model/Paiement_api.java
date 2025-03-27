package com.example.bimix.model;

import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.util.List;
import java.util.Optional;

public class Paiement_api {
    private Optional<Depart> depart;
    private List<Itineraire> itineraires;
    private Optional<Paiement> paiement;
    private List<Paiement_situation> paiement_situations;

    public Paiement_api(Optional<Depart> depart, List<Itineraire> itineraires, Optional<Paiement> paiement, List<Paiement_situation> paiement_situations) {
        this.depart = depart;
        this.itineraires = itineraires;
        this.paiement = paiement;
        this.paiement_situations = paiement_situations;
    }

    public Optional<Depart> getDepart() {
        return depart;
    }

    public void setDepart(Optional<Depart> depart) {
        this.depart = depart;
    }

    public List<Itineraire> getItineraires() {
        return itineraires;
    }

    public void setItineraires(List<Itineraire> itineraires) {
        this.itineraires = itineraires;
    }

    public Optional<Paiement> getPaiement() {
        return paiement;
    }

    public void setPaiement(Optional<Paiement> paiement) {
        this.paiement = paiement;
    }

    public List<Paiement_situation> getPaiement_situations() {
        return paiement_situations;
    }

    public void setPaiement_situations(List<Paiement_situation> paiement_situations) {
        this.paiement_situations = paiement_situations;
    }
}
