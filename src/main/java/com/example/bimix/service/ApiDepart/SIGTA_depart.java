package com.example.bimix.service.ApiDepart;

import com.example.bimix.model.*;
import com.example.bimix.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SIGTA_depart {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private Fonction_personnelRepository fonction_personnelRepository;

    @Autowired
    private Service_PersonnelRepository servicePersonnelRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private TransportRepository transportRepository;

    public Optional<Depart_Api> getDepartByOr(String token, String request) {
        String url = "http://localhost:8080/Professeur/SelectAll_Professeur";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(request, headers);

//        ResponseEntity<String> response = restTemplate.exchange(
//                url, HttpMethod.POST, requestEntity, String.class);

        //return response.getBody();
        Optional<Personnel> personnel = this.personnelRepository.findPersonIM("209208");

        Optional<Fonction_personnel> fonction_personnel = this.fonction_personnelRepository.findFonction_personnelMaxByIdPersonnel(personnel.get().getIdPersonnel());
        Optional<Service_Personnel> service_personnel = this.servicePersonnelRepository.findService_PersonnelMaxByIdPersonnel(personnel.get().getIdPersonnel());

        if (fonction_personnel.isEmpty()){
            fonction_personnel.isEmpty();
        }
        if (service_personnel.isEmpty()){
            service_personnel.isEmpty();
        }
        Soa_personne soa_personne = new Soa_personne(fonction_personnel.get() , service_personnel.get());
        Depart depart = new Depart();
        depart.setNumero_OR(request);
        depart.setIdPersonne(personnel.get());
        depart.setObjet_mission("Asa");
        depart.setDate_depart(Date.valueOf("2025-03-25"));
        depart.setDate_arriver(Date.valueOf("2025-04-25"));
        depart.setCode_Visa_depart("qwerty");
        depart.setCode_avance("azerty");
        depart.setEngagement(Date.valueOf("2025-04-25"));
        depart.setBordereau("zaq");
        depart.setSoa("qqqq");
        Optional<Region> region1 = this.regionRepository.findById(1);
        Optional<Region> region2 = this.regionRepository.findById(2);
        Optional<Transport> transport = this.transportRepository.findById(1);
        List<Itineraire> itineraires = new ArrayList<>();
        Itineraire itineraire1 = new Itineraire();
        itineraire1.setIdDepart(depart);
        itineraire1.setNumero(1);
        itineraire1.setIdRegion_depart(region1.get());
        itineraire1.setIdRegion_arriver(region2.get());
        itineraire1.setIdTransport(transport.get());
        Itineraire itineraire2 = new Itineraire();
        itineraire2.setIdDepart(depart);
        itineraire2.setNumero(2);
        itineraire2.setIdRegion_depart(region2.get());
        itineraire2.setIdRegion_arriver(region1.get());
        itineraire2.setIdTransport(transport.get());
        itineraires.add(itineraire1);
        itineraires.add(itineraire2);
        return Optional.of(new Depart_Api(depart, itineraires , soa_personne));
    }
}