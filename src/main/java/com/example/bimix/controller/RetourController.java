package com.example.bimix.controller;

import com.example.bimix.configuration.JWTManager;
import com.example.bimix.model.*;
import com.example.bimix.service.ApiPassage.SIGTA_passage;
import com.example.bimix.service.ApiRetour.SIGTA_retour;
import com.example.bimix.service.DepartSercvice;
import com.example.bimix.service.PassageService;
import com.example.bimix.service.RetourService;
import com.example.bimix.service.UtilisateurService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Retour")
public class RetourController {
    @Autowired
    RetourService retourService;

    @Autowired
    JWTManager jwtManager;

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    DepartSercvice departSercvice;

    @Autowired
    SIGTA_retour sigta_retour;

    @Transactional
    @PostMapping("/insertion_API_retour")
    public ResponseEntity<HashMap> insertion_API_retour(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String Or = data.get("Or");
        String date_visa_fin = data.get("date_visa_fin");
        String token = data.get("token");
        Optional<Retour_Api> retour_api = this.sigta_retour.getRetourByOr("" , Or);
        Optional<Utilisateur> utilisateur = this.utilisateurService.select_Utilisateur_By_id(Integer.parseInt(jwtManager.getClaim(String.valueOf(token), "id")));
        Depart depart = retour_api.get().getDepart();
        Optional<Depart> verifier_OR = this.departSercvice.findDepartBynumero_OR(depart.getNumero_OR());
        Optional<Retour> verifier_OR_Retour = this.retourService.findRetourBynumero_OR(Or);
        Optional<Retour> verifier_visa_Fin = this.retourService.findRetourByCode_visa_fin(Or);
        if (verifier_OR.isEmpty()){
            result.put("Erreur" , "Cette OR n'a pas encore été enregistrée, son départ.");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        if (verifier_visa_Fin.isPresent()){
            result.put("Erreur" , "Ce code Visa fin a été déjà enregistré.");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        if (verifier_OR_Retour.isPresent()){
            result.put("Erreur" , "Cette OR a déjà fait un enregistrement pour son retour.");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        Retour r = new Retour();
        r.setNumero_OR(depart.getNumero_OR());
        r.setIdPersonne(depart.getIdPersonne());
        r.setObjet_mission(depart.getObjet_mission());
        r.setDate_visa_fin(Date.valueOf(date_visa_fin));
        r.setCode_visa_passage(depart.getCode_Visa_depart());
        r.setIdUtilisateur(utilisateur.get());
        r.setDates(new Date(new java.util.Date().getTime()));
        try {
            Retour retour = this.retourService.enregistrerRetour(r);
            result.put("data", "Retour Enregistrer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.print("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de l'insertion du depart.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @GetMapping("/API_retour/{or}")
    public ResponseEntity<HashMap> API_retour(@PathVariable("or") String or) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            Optional<Retour_Api> retour_api = this.sigta_retour.getRetourByOr("" , or);
            result.put("data",retour_api);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @GetMapping("/select_retour")
    public ResponseEntity<HashMap> select_retour(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String token = data.get("token");
        String lim = data.get("lim");
        Optional<Utilisateur> utilisateur = this.utilisateurService.select_Utilisateur_By_id(Integer.parseInt(jwtManager.getClaim(String.valueOf(token), "id")));
        try {
            if (utilisateur.get().getIdRole().getIdRole() == 1){
                List<Retour> retours = this.retourService.findRetourLimiter(Integer.parseInt(lim));
                result.put("data",retours);
            }
            if (utilisateur.get().getIdRole().getIdRole() == 2){
                List<Retour> retours = this.retourService.findRetourByIdUtilisateurLim(utilisateur.get().getIdUtilisateur() , Integer.parseInt(lim));
                result.put("data",retours);
            }else {
                result.put("data","null");
            }
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
