package com.example.bimix.service.ApiRetour;

import com.example.bimix.model.*;
import com.example.bimix.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class SIGTA_retour {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DepartRepository departRepository;

    @Autowired
    private Fonction_personnelRepository fonction_personnelRepository;

    @Autowired
    private Service_PersonnelRepository servicePersonnelRepository;


    public Optional<Retour_Api> getRetourByOr(String token, String request) {
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
        String code = "12345";
        return Optional.of(new Retour_Api(depart.get(), soa_personne ,code));
    }
}
