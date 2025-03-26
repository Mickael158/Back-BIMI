package com.example.bimix.model;


import jakarta.persistence.*;

@Entity
@Table(name = "itineraire")
public class Itineraire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iditineraire", nullable = false)
    private Integer idItineraire;

    @Column(name = "numero")
    private int numero;

    @ManyToOne
    @JoinColumn(name = "iddepart")
    private Depart idDepart;

    @ManyToOne
    @JoinColumn(name = "idregion_depart")
    private Region idRegion_depart;

    @ManyToOne
    @JoinColumn(name = "idregion_arriver")
    private Region idRegion_arriver;

    @ManyToOne
    @JoinColumn(name = "idtransport")
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
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
