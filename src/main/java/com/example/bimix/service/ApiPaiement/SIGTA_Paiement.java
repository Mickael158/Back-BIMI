package com.example.bimix.service.ApiPaiement;

import com.example.bimix.model.*;
import com.example.bimix.repository.DepartRepository;
import com.example.bimix.repository.Fonction_personnelRepository;
import com.example.bimix.repository.ItineraireRepository;
import com.example.bimix.repository.Service_PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SIGTA_Paiement {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DepartRepository departRepository;

    @Autowired
    private Fonction_personnelRepository fonction_personnelRepository;

    @Autowired
    private Service_PersonnelRepository servicePersonnelRepository;

    @Autowired
    private ItineraireRepository itineraireRepository;


    public Optional<Depart_Api> getPaiementByOr(String token, String request) {
        String url = "http://localhost:8080/Professeur/SelectAll_Professeur";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(request, headers);

//        ResponseEntity<String> response = restTemplate.exchange(
//                url, HttpMethod.POST, requestEntity, String.class);

        //return response.getBody();
        Optional<Depart> depart = this.departRepository.findDepartBynumero_OR(request);
        Optional<Fonction_personnel> fonction_personnel = this.fonction_personnelRepository.findFonction_personnelMaxByIdPersonnel(depart.get().getIdPersonne().getIdPersonnel());
        Optional<Service_Personnel> service_personnel = this.servicePersonnelRepository.findService_PersonnelMaxByIdPersonnel(depart.get().getIdPersonne().getIdPersonnel());
        if (fonction_personnel.isEmpty()){
            fonction_personnel.isEmpty();
        }
        if (service_personnel.isEmpty()){
            service_personnel.isEmpty();
        }
        Soa_personne soa_personne = new Soa_personne(fonction_personnel.get() , service_personnel.get());
        List<Itineraire> itineraires = this.itineraireRepository.findItineraireByIdDepart(depart.get().getIdDepart());
        return Optional.of(new Depart_Api(depart.get() ,itineraires ,soa_personne));
    }
}
