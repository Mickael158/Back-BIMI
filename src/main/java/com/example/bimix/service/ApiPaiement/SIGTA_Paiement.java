package com.example.bimix.service.ApiPaiement;

import com.example.bimix.model.Depart;
import com.example.bimix.model.Depart_Api;
import com.example.bimix.model.Itineraire;
import com.example.bimix.model.Retour_Api;
import com.example.bimix.repository.DepartRepository;
import com.example.bimix.repository.ItineraireRepository;
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
        List<Itineraire> itineraires = this.itineraireRepository.findItineraireByIdDepart(depart.get().getIdDepart());
        return Optional.of(new Depart_Api(depart.get() ,itineraires));
    }
}
