package com.example.bimix.service.ApiPassage;

import com.example.bimix.model.*;
import com.example.bimix.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SIGTA_passage {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private DepartRepository departRepository;

    @Autowired
    private ItineraireRepository itineraireRepository;

    public Optional<Passage_Api> getPassageNextByOr(String token, String request) {
        String url = "http://localhost:8080/Professeur/SelectAll_Professeur";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(request, headers);

//        ResponseEntity<String> response = restTemplate.exchange(
//                url, HttpMethod.POST, requestEntity, String.class);

        //return response.getBody();
        Optional<Depart> depart = this.departRepository.findDepartBynumero_OR(request);
        Optional<Itineraire> itineraire = this.itineraireRepository.findItineraireNext(request);
        String code = "12345";
        return Optional.of(new Passage_Api(depart.get(), itineraire.get() ,code));
    }
}