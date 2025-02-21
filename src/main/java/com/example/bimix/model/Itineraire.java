package com.example.bimix.model;


import jakarta.persistence.*;

@Entity
@Table(name = "itineraire")
public class Itineraire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idItineraire", nullable = false)
    private Integer idItineraire;

    @Column(name = "numero")
    private String numero;

    @ManyToOne
    @JoinColumn(name = "idDepart")
    private Depart idDepart;

    @ManyToOne
    @JoinColumn(name = "idRegion_depart")
    private Region idRegion_depart;

    @ManyToOne
    @JoinColumn(name = "idRegion_arriver")
    private Region idRegion_arriver;

    @ManyToOne
    @JoinColumn(name = "idTransport")
    private Transport idTransport;

    public Transport getIdTransport() {
        return idTransport;
    }

    public void setIdTransport(Transport idTransport) {
        this.idTransport = idTransport;
    }

    public Integer getIdItineraire() {
        return idItineraire;
    }

    public void setIdItineraire(Integer idItineraire) {
        this.idItineraire = idItineraire;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Depart getIdDepart() {
        return idDepart;
    }

    public void setIdDepart(Depart idDepart) {
        this.idDepart = idDepart;
    }

    public Region getIdRegion_depart() {
        return idRegion_depart;
    }

    public void setIdRegion_depart(Region idRegion_depart) {
        this.idRegion_depart = idRegion_depart;
    }

    public Region getIdRegion_arriver() {
        return idRegion_arriver;
    }

    public void setIdRegion_arriver(Region idRegion_arriver) {
        this.idRegion_arriver = idRegion_arriver;
    }
}
