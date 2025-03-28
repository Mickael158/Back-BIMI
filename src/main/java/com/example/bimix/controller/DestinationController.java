package com.example.bimix.controller;


import com.example.bimix.configuration.JWTManager;
import com.example.bimix.model.*;
import com.example.bimix.service.ApiDestination.SIGTA_destination;
import com.example.bimix.service.ApiPassage.SIGTA_passage;
import com.example.bimix.service.DepartSercvice;
import com.example.bimix.service.DestionService;
import com.example.bimix.service.PassageService;
import com.example.bimix.service.UtilisateurService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.Optional;

@Controller
@RequestMapping("/Destination")
public class DestinationController {
    @Autowired
    DestionService destionService;

    @Autowired
    JWTManager jwtManager;

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    DepartSercvice departSercvice;

    @Autowired
    SIGTA_destination sigta_destination;

    @Transactional
    @PostMapping("/insertion_API_destination")
    public ResponseEntity<HashMap> insertion_API_destination(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String Or = data.get("Or");
        String date_arriver_destination = data.get("date_arriver_destination");
        String token = data.get("token");
        Optional<Destination_Api> destination_api = this.sigta_destination.getDestinationByOr("" , Or);
        Optional<Utilisateur> utilisateur = this.utilisateurService.select_Utilisateur_By_id(Integer.parseInt(jwtManager.getClaim(String.valueOf(token), "id")));
        Depart depart = destination_api.get().getDepart();
        Optional<Depart> verifier_OR_Depart = this.departSercvice.findDepartBynumero_OR(Or);
        Optional<Destination> verifier_Or_Destination = this.destionService.findDestinationBynumero_OR(Or);
        Optional<Destination> verifier_Code_visa_destination = this.destionService.findDestinationByCode_visa_destination(depart.getCode_Visa_depart());
        System.out.println("QQQQ");
        if (verifier_OR_Depart.isEmpty()){
            result.put("Erreur" , "Cette OR n'a pas encore été enregistrée, son départ.");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        if (verifier_Or_Destination.isPresent()){
            result.put("Erreur" , "Cette Or est déjà fait une destination");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        if (verifier_Code_visa_destination.isPresent()){
            result.put("Erreur" , "Cette Code visa destination est déjà fait une destination");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        Itineraire itineraire = destination_api.get().getItineraires();
        Destination d = new Destination();
        d.setDates(new Date(new java.util.Date().getTime()));
        d.setNumero_OR(depart.getNumero_OR());
        d.setIdPersonne(depart.getIdPersonne());
        d.setObjet_mission(depart.getObjet_mission());
        d.setDate_arriver_destination(Date.valueOf(date_arriver_destination));
        d.setCode_visa_passage(destination_api.get().getCode_visa_destination());
        d.setIdUtilisateur(utilisateur.get());
        d.setIdItineraire(itineraire);
        try {
            Destination destination = this.destionService.enregistrerDestination(d);
            result.put("data", "Destination Enregistrer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , "Une erreur s'est produite lors de l'insertion du depart.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @GetMapping("/API_destination/{or}")
    public ResponseEntity<HashMap> API_destination(@PathVariable("or") String or) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {

            Optional<Destination_Api> passage_api = this.sigta_destination.getDestinationByOr("" , or);
            result.put("data",passage_api);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
