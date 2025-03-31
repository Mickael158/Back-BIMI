package com.example.bimix.controller;

import com.example.bimix.configuration.JWTManager;
import com.example.bimix.model.*;
import com.example.bimix.service.*;
import com.example.bimix.service.ApiDepart.SIGTA_depart;
import com.example.bimix.service.ApiPaiement.SIGTA_Paiement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Paiement")
public class PaiementController {
    @Autowired
    PaiementService paiementService;

    @Autowired
    ItineraireService itineraireService;

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    PersonnelService personnelService;

    @Autowired
    Paiement_SituationService paiement_situationService;

    @Autowired
    Modif_paiementService modif_paiementService;

    @Autowired
    RegionService regionService;

    @Autowired
    TransportService transportService;

    @Autowired
    JWTManager jwtManager;

    @Autowired
    SIGTA_Paiement sigta_paiement;

    @Transactional
    @PostMapping("/insertion_API_paiement")
    public ResponseEntity<HashMap> insertion_API_paiement(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String Or = data.get("Or");
        String date_sortie_bon_de_caisse = data.get("date_sortie_bon_de_caisse");
        String date_premier_suivi = data.get("date_premier_suivi");
        String situation_tresor = data.get("situation_tresor");
        String token = data.get("token");

        Optional<Utilisateur> utilisateur = this.utilisateurService.select_Utilisateur_By_id(Integer.parseInt(jwtManager.getClaim(String.valueOf(token), "id")));
        Optional<Paiement_api> paiement_api = this.paiementService.selectPaimentByOr("098765");
        if (paiement_api.get().getPaiement().isPresent() && paiement_api.get().getItineraires().size() != 0 && paiement_api.get().getDepart().isPresent()){
            Modif_paiement mp = new Modif_paiement();
            String modif = "Or : "+ Or + " date_sortie_bon_de_caisse : " + date_sortie_bon_de_caisse + " date_premier_suivi : " + date_premier_suivi + " situation_tresor : " + situation_tresor;
            mp.setModif(modif);
            mp.setIdUtilisateur(utilisateur.get());
            mp.setDates(new Date(new java.util.Date().getTime()));
            Paiement_situation ps = new Paiement_situation();
            ps.setDates(new Date(new java.util.Date().getTime()));
            ps.setSituation(situation_tresor);
            ps.setIdpaiement(paiement_api.get().getPaiement().get());
            ps.setIdUtilisateur(utilisateur.get());
            mp.setIdpaiement(paiement_api.get().getPaiement().get());
            try {
                this.paiementService.updatePaiement(Date.valueOf(date_premier_suivi), Date.valueOf(date_sortie_bon_de_caisse), paiement_api.get().getPaiement().get().getIdPaiement());
                this.paiement_situationService.enregistrerPaiement_situation(ps);
                this.modif_paiementService.enregistrerModif_paiement(mp);
                result.put("data", "Mise a jour du Paiement Enregistrer");
                return new ResponseEntity<>(result , HttpStatus.OK);
            }catch (Exception e) {
                System.out.print("Erreur" + e.getMessage());
                result.put("Erreur" , "Une erreur s'est produite lors de la Mise a jour du Paiement.");
            }
        }

        Optional<Depart_Api> depart_api = this.sigta_paiement.getPaiementByOr("" , Or);
        Depart depart = depart_api.get().getDepart();
        Paiement_situation ps = new Paiement_situation();
        Modif_paiement mp = new Modif_paiement();
        Paiement p = new Paiement();
        p.setDates(new Date(new java.util.Date().getTime()));
        p.setIddepart(depart);
        p.setDate_suivit(Date.valueOf(date_premier_suivi));
        p.setDate_sortie_bon_de_caisse(Date.valueOf(date_sortie_bon_de_caisse));
        ps.setDates(new Date(new java.util.Date().getTime()));
        ps.setSituation(situation_tresor);
        ps.setIdpaiement(p);
        ps.setIdUtilisateur(utilisateur.get());
        mp.setDates(new Date(new java.util.Date().getTime()));
        String modif = "Or : "+ Or + " date_sortie_bon_de_caisse : " + date_sortie_bon_de_caisse + " date_premier_suivi : " + date_premier_suivi + " situation_tresor : " + situation_tresor;
        mp.setModif(modif);
        mp.setIdUtilisateur(utilisateur.get());
        mp.setIdpaiement(p);
        try {
            this.paiementService.enregistrerPaiement(p);
            this.paiement_situationService.enregistrerPaiement_situation(ps);
            this.modif_paiementService.enregistrerModif_paiement(mp);
            result.put("data", "Paiement Enregistrer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.print("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de l'insertion du depart.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/API_paiement/{or}")
    public ResponseEntity<HashMap> API_paiement(@PathVariable("or") String or) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            Optional<Paiement_api> paiement_api = this.paiementService.selectPaimentByOr(or);
            Optional<Depart_Api> depart_api = this.sigta_paiement.getPaiementByOr("aaa" , or);
            if (paiement_api.get().getPaiement().isPresent() && paiement_api.get().getItineraires().size() != 0 && paiement_api.get().getDepart().isPresent()){
                result.put("data",paiement_api);
            }else {
                result.put("data",depart_api);
            }
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/select_paiement")
    public ResponseEntity<HashMap> select_paiement(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String token = data.get("token");
        String lim = data.get("lim");
        Optional<Utilisateur> utilisateur = this.utilisateurService.select_Utilisateur_By_id(Integer.parseInt(jwtManager.getClaim(String.valueOf(token), "id")));
        try {
            if (utilisateur.get().getIdRole().getIdRole() == 1){
                List<Paiement> paiements = this.paiementService.findPaiementLimiter(Integer.parseInt(lim));
                result.put("data",paiements);
            }
            if (utilisateur.get().getIdRole().getIdRole() == 2){
                List<Paiement> paiements = this.paiementService.findPaiementByIdUtilisateurLim(utilisateur.get().getIdUtilisateur() , Integer.parseInt(lim));
                result.put("data",paiements);
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
