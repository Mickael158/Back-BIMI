package com.example.bimix.controller;

import com.example.bimix.configuration.JWTManager;
import com.example.bimix.model.*;
import com.example.bimix.service.*;
import com.example.bimix.service.ApiDepart.SIGTA_depart;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.*;

@Controller
@RequestMapping("/Depart")
public class DepartController {
    @Autowired
    DepartSercvice departSercvice;

    @Autowired
    ItineraireService itineraireService;

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    PersonnelService personnelService;

    @Autowired
    RegionService regionService;

    @Autowired
    TransportService transportService;

    @Autowired
    JWTManager jwtManager;

    @Autowired
    SIGTA_depart sigta_depart;

    @Transactional
    @PostMapping("/insertion_API_depart")
    public ResponseEntity<HashMap> insertion_API_depart(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String Or = data.get("Or");
        String date_debut = data.get("date_debut");
        String date_fin = data.get("date_fin");
        String token = data.get("token");
        Optional<Depart_Api> depart_api = this.sigta_depart.getDepartByOr("aaa" , Or);
        Optional<Utilisateur> utilisateur = this.utilisateurService.select_Utilisateur_By_id(Integer.parseInt(jwtManager.getClaim(String.valueOf(token), "id")));
        Depart depart = depart_api.get().getDepart();
        Optional<Depart> verifier_OR = this.departSercvice.findDepartBynumero_OR(depart.getNumero_OR());
        Optional<Depart> verifier_Bordereau = this.departSercvice.findDepartByBordereau(depart.getBordereau());
        Optional<Depart> verifier_code_Visa_depart = this.departSercvice.findDepartBycode_Visa_depart(depart.getCode_Visa_depart());
        if (verifier_OR.isPresent()){
            result.put("Erreur" , "Cette OR est déjà enregistrée.");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        if (verifier_Bordereau.isPresent()){
            result.put("Erreur" , "Ce bordereau est déjà enregistré.");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        if (verifier_code_Visa_depart.isPresent()){
            result.put("Erreur" , "Ce code Visa départ est déjà enregistré.");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        depart.setDate_depart(Date.valueOf(date_debut));
        depart.setDate_arriver(Date.valueOf(date_fin));
        depart.setIdUtilisateur(utilisateur.get());
        depart.setDates(new Date(new java.util.Date().getTime()));
        List<Itineraire> itineraires = depart_api.get().getItineraires();
        try {
            this.departSercvice.enregistrerDepart(depart);
            List<Itineraire> itineraireList = this.itineraireService.enregistrerItineraires(itineraires);
            result.put("data", "Depart Enregistrer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.print("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de l'insertion du depart.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/API_depart/{or}")
    public ResponseEntity<HashMap> API_depart(@PathVariable("or") String or) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            Optional<Depart_Api> depart_api = this.sigta_depart.getDepartByOr("aaa" , or);
            result.put("data",depart_api);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/select_depart")
    public ResponseEntity<HashMap> select_depart(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String token = data.get("token");
        String lim = data.get("lim");
        List<Depart_select> depart_selects = new ArrayList<>();
        Optional<Utilisateur> utilisateur = this.utilisateurService.select_Utilisateur_By_id(Integer.parseInt(jwtManager.getClaim(String.valueOf(token), "id")));
        try {
            if (utilisateur.get().getIdRole().getIdRole() == 1){
                List<Depart> depart = this.departSercvice.findDepartLim(Integer.parseInt(lim));
                for (Depart d : depart){
                    List<Itineraire> itineraires = this.itineraireService.findItineraireByIdDepart(d.getIdDepart());
                    Depart_select depart_select = new Depart_select();
                    depart_select.setIdDepart(d.getIdDepart());
                    depart_select.setDates(d.getDates());
                    depart_select.setNumero_OR(d.getNumero_OR());
                    depart_select.setIdPersonne(d.getIdPersonne());
                    depart_select.setObjet_mission(d.getObjet_mission());
                    depart_select.setDate_depart(d.getDate_depart());
                    depart_select.setDate_arriver(d.getDate_arriver());
                    depart_select.setCode_Visa_depart(d.getCode_Visa_depart());
                    depart_select.setCode_avance(d.getCode_avance());
                    depart_select.setEngagement(d.getEngagement());
                    depart_select.setBordereau(d.getBordereau());
                    depart_select.setSoa(d.getSoa());
                    depart_select.setItineraires(itineraires);
                    depart_selects.add(depart_select);
                }
                result.put("data",depart_selects);
            }
            if (utilisateur.get().getIdRole().getIdRole() == 2){
                List<Depart> depart = this.departSercvice.findDepartByIdUtilisateurAndLimit( utilisateur.get().getIdUtilisateur() ,Integer.parseInt(lim));
                for (Depart d : depart){
                    List<Itineraire> itineraires = this.itineraireService.findItineraireByIdDepart(d.getIdDepart());
                    Depart_select depart_select = new Depart_select();
                    depart_select.setIdDepart(d.getIdDepart());
                    depart_select.setDates(d.getDates());
                    depart_select.setNumero_OR(d.getNumero_OR());
                    depart_select.setIdPersonne(d.getIdPersonne());
                    depart_select.setObjet_mission(d.getObjet_mission());
                    depart_select.setDate_depart(d.getDate_depart());
                    depart_select.setDate_arriver(d.getDate_arriver());
                    depart_select.setCode_Visa_depart(d.getCode_Visa_depart());
                    depart_select.setCode_avance(d.getCode_avance());
                    depart_select.setEngagement(d.getEngagement());
                    depart_select.setBordereau(d.getBordereau());
                    depart_select.setSoa(d.getSoa());
                    depart_select.setItineraires(itineraires);
                    depart_selects.add(depart_select);
                }
                result.put("data",depart_selects);
            } else {
                result.put("data","null");
            }
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @DeleteMapping("/delete_Depart_by_Id/{id}")
    public ResponseEntity<HashMap> delete_Depart_by_Id(@PathVariable("id") int id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            this.departSercvice.delete_Depart_By_id(id);
            result.put("data","Depart supprimer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.println("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de la suppression du Depart.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
