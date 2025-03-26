package com.example.bimix.controller;

import com.example.bimix.configuration.JWTManager;
import com.example.bimix.model.*;
import com.example.bimix.service.*;
import com.example.bimix.service.ApiDepart.SIGTA_depart;
import com.example.bimix.service.ApiPassage.SIGTA_passage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Passage")
public class PassageController {
    @Autowired
    PassageService passageService;

    @Autowired
    JWTManager jwtManager;

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    DepartSercvice departSercvice;

    @Autowired
    SIGTA_passage sigta_passage;

    @Transactional
    @PostMapping("/insertion_API_passage")
    public ResponseEntity<HashMap> insertion_CatOR(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String Or = data.get("Or");
        String date_passage = data.get("date_passage");
        String token = data.get("token");
        Optional<Passage_Api> passage_api = this.sigta_passage.getPassageNextByOr("" , Or);
        Optional<Utilisateur> utilisateur = this.utilisateurService.select_Utilisateur_By_id(Integer.parseInt(jwtManager.getClaim(String.valueOf(token), "id")));
        Depart depart = passage_api.get().getDepart();
        Optional<Depart> verifier_OR = this.departSercvice.findDepartBynumero_OR(depart.getNumero_OR());
        Optional<Passage> verifier_Code_visa_passage = this.passageService.findPassageByCode_visa_passage(passage_api.get().getCode_visa_passage());
        if (verifier_OR.isEmpty()){
            result.put("Erreur" , "Cette OR n'a pas encore été enregistrée, son départ.");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        if (verifier_Code_visa_passage.isPresent()){
            result.put("Erreur" , "Ce code Visa passage a été déjà enregistré.");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        Itineraire itineraire = passage_api.get().getItineraires();
        Passage p = new Passage();
        p.setNumero_OR(depart.getNumero_OR());
        p.setIdPersonne(depart.getIdPersonne());
        p.setIdItineraire(itineraire);
        p.setCode_visa_passage(passage_api.get().getCode_visa_passage());
        p.setDate_passage(Date.valueOf(date_passage));
        p.setIdUtilisateur(utilisateur.get());
        p.setDates(new Date(new java.util.Date().getTime()));
        p.setObjet_mission(depart.getObjet_mission());
        try {
            Passage passage = this.passageService.enregistrerPassage(p);
            result.put("data", "Passage Enregistrer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.print("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de l'insertion du depart.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @GetMapping("/API_passage")
    public ResponseEntity<HashMap> API_depart() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            Optional<Passage_Api> passage_api = this.sigta_passage.getPassageNextByOr("" , "098765");
            result.put("data",passage_api);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
