package com.example.bimix.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "service_personnel")
public class Service_Personnel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idservice_personnel", nullable = false)
    private Integer idService_Personnel;

    @Column(name = "dates")
    private Date dates;

    @ManyToOne
    @JoinColumn(name = "idpersonnel")
    private Personnel idPersonnel;

    @ManyToOne
    @JoinColumn(name = "idservice")
    private ServiceM idService;

    public Integer getIdService_Personnel() {
        return idService_Personnel;
    }

    public void setIdService_Personnel(Integer idService_Personnel) {
        this.idService_Personnel = idService_Personnel;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public Personnel getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(Personnel idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public ServiceM getIdService() {
        return idService;
    }

    public void setIdService(ServiceM idService) {
        this.idService = idService;
    }
}
