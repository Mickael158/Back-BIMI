package com.example.bimix.service.ApiDestination;

import com.example.bimix.model.Depart;
import com.example.bimix.model.Destination_Api;
import com.example.bimix.model.Itineraire;
import com.example.bimix.model.Passage_Api;
import com.example.bimix.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class SIGTA_destination {
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

    public Optional<Destination_Api> getDestinationByOr(String token, String request) {
        String url = "http://localhost:8080/Professeur/SelectAll_Professeur";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Content-Type", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(request, headers);

//        ResponseEntity<String> response = restTemplate.exchange(
//                url, HttpMethod.POST, requestEntity, String.class);

        //return response.getBody();
        Optional<Depart> depart = this.departRepository.findDepartBynumero_OR(request);

        Optional<Itineraire> itineraire = this.itineraireRepository.findItineraireFin(request);
        System.out.println(depart.get());
        String code = "12345";
        return Optional.of(new Destination_Api(depart.get(), itineraire.get() ,code));
    }
}
