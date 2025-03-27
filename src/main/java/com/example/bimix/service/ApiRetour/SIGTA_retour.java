package com.example.bimix.service.ApiRetour;

import com.example.bimix.model.Depart;
import com.example.bimix.model.Itineraire;
import com.example.bimix.model.Passage_Api;
import com.example.bimix.model.Retour_Api;
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
        String code = "12345";
        return Optional.of(new Retour_Api(depart.get() ,code));
    }
}
