package com.example.bimix.controller;

import com.example.bimix.configuration.JWTManager;
import com.example.bimix.model.*;
import com.example.bimix.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

//    @Transactional
//    @PostMapping("/insertion_Depart")
//    public ResponseEntity<HashMap> insertion_Depart(@RequestBody HashMap<String , String> data) throws Exception {
//        HashMap<String, Object> result = new HashMap<>();
//        String IM_mission = data.get("IM_mission");
//        String IdUtilisateur = data.get("IdUtilisateur");
//        String IdPersonne = data.get("IdPersonne");
//        String numero_OR = data.get("numero_OR");
//        String date_depart = data.get("date_depart");
//        String jour = data.get("jour");
//        String code_Visa = data.get("code_Visa");
//        String avance = data.get("avance");
//        Optional<Utilisateur> utilisateur = this.utilisateurService.select_Utilisateur_By_id(Integer.parseInt(IdUtilisateur));
//        Optional<Personnel> personnel = this.personnelService.select_Personnel_By_id(Integer.parseInt(IdPersonne));
//        Date date_arriver = this.departSercvice.ajouterJour(Timestamp.valueOf(date_depart), Integer.parseInt(jour));
//        Depart d = new Depart();
//        d.setDates(new Date(new java.util.Date().getTime()));
//        d.setIM_mission(IM_mission);
//        d.setIdUtilisateur(utilisateur.get());
//        d.setIdPersonne(personnel.get());
//        d.setNumero_OR(numero_OR);
//        d.setDate_depart(Timestamp.valueOf(date_depart));
//        d.setDate_arriver(date_arriver);
//        d.setAvance(avance);
//        try {
//            Depart depart = this.departSercvice.enregistrerDepart(d);
//            result.put("data", "Catégorie Org Enregistrer");
//            return new ResponseEntity<>(result , HttpStatus.OK);
//        }catch (Exception e) {
//            System.out.print("Erreur" + e.getMessage());
//            result.put("Erreur" , "Une erreur s'est produite lors de l'insertion de la catégorie Org.");
//        }
//        return new ResponseEntity<>(result , HttpStatus.OK);
//    }
//    @PostMapping("/enregistrerTableausPvs")
//    public ResponseEntity<HashMap> enregistrerTableausPv(@RequestBody List<HashMap<String , String>> datas) throws Exception {
//        HashMap<String, Object> result = new HashMap<>();
//        List<Itineraire> itineraires = new ArrayList<>();
//        for (HashMap<String , String> data : datas){
//            String numero = data.get("numero");
//            String idRegion_depart = data.get("idRegion_depart");
//            String idRegion_arriver = data.get("idRegion_arriver");
//            String idTransport = data.get("idTransport");
//            Optional<Region> region_depart = this.regionService.select_Region_By_id(Integer.parseInt(idRegion_depart));
//            Optional<Region> region_arriver = this.regionService.select_Region_By_id(Integer.parseInt(idRegion_arriver));
//            Itineraire i = new Itineraire();
//            i.setNumero(numero);
//            i.setIdRegion_depart(region_depart.get());
//            i.setIdRegion_arriver(region_arriver.get());
//            itineraires.add(i);
//        }
//        try {
//            List<Itineraire> itineraireList = this.itineraireService.enregistrerItineraires(itineraires);
//            result.put("data",itineraireList);
//            return new ResponseEntity<>(result , HttpStatus.OK);
//        }catch (Exception e) {
//            result.put("Erreur" , e.getMessage());
//        }
//        return new ResponseEntity<>(result , HttpStatus.OK);
//    }
    @Transactional
    @PostMapping("/insertion_Depart")
    public ResponseEntity<HashMap> insertionEtEnregistrement(@RequestBody HashMap<String, Object> requestData) throws Exception {
        HashMap<String, Object> result = new HashMap<>();

        // Traitement de l'insertion de départ
        HashMap<String, String> departData = (HashMap<String, String>) requestData.get("depart");
        String IM_mission = departData.get("IM_mission");
        String token = departData.get("token");
        String idUtilisateur = jwtManager.getClaim(String.valueOf(token), "id");
        String IdPersonne = departData.get("IdPersonne");
        String numero_OR = departData.get("numero_OR");
        String date_depart = departData.get("date_depart");
        String jour = departData.get("jour");
        String avance = departData.get("avance");

        Optional<Utilisateur> utilisateur = this.utilisateurService.select_Utilisateur_By_id(Integer.parseInt(idUtilisateur));
        Optional<Personnel> personnel = this.personnelService.select_Personnel_By_id(Integer.parseInt(IdPersonne));
        Date date_arriver = this.departSercvice.ajouterJour(Timestamp.valueOf(date_depart), Integer.parseInt(jour));

        Depart d = new Depart();
        d.setDates(new Date(new java.util.Date().getTime()));
        d.setIM_mission(IM_mission);
        d.setIdUtilisateur(utilisateur.get());
        d.setIdPersonne(personnel.get());
        d.setNumero_OR(numero_OR);
        d.setDate_depart(Timestamp.valueOf(date_depart));
        d.setDate_arriver(date_arriver);
        d.setAvance(avance);

        try {
            this.departSercvice.enregistrerDepart(d);
            result.put("departMessage", "Départ enregistré avec succès");
        } catch (Exception e) {
            result.put("departErreur", "Erreur lors de l'enregistrement du départ: " + e.getMessage());
        }

        // Traitement de l'enregistrement des itinéraires
        List<HashMap<String, String>> itinerairesData = (List<HashMap<String, String>>) requestData.get("itineraires");
        List<Itineraire> itineraires = new ArrayList<>();

        for (HashMap<String, String> data : itinerairesData) {
            String numero = data.get("numero");
            String idRegion_depart = data.get("idRegion_depart");
            String idRegion_arriver = data.get("idRegion_arriver");
            String idTransport = data.get("idTransport");

            Optional<Region> region_depart = this.regionService.select_Region_By_id(Integer.parseInt(idRegion_depart));
            Optional<Region> region_arriver = this.regionService.select_Region_By_id(Integer.parseInt(idRegion_arriver));
            Optional<Transport> transport = this.transportService.select_Transport_By_id(Integer.parseInt(idTransport));

            Itineraire i = new Itineraire();
            i.setNumero(numero);
            i.setIdRegion_depart(region_depart.get());
            i.setIdRegion_arriver(region_arriver.get());
            i.setIdDepart(d);
            i.setIdTransport(transport.get());
            itineraires.add(i);
        }

        try {
            List<Itineraire> itineraireList = this.itineraireService.enregistrerItineraires(itineraires);
            result.put("itineraires", itineraireList);
        } catch (Exception e) {
            result.put("itinerairesErreur", "Erreur lors de l'enregistrement des itinéraires: " + e.getMessage());
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
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
