package com.example.bimix.controller;

import com.example.bimix.model.*;
import com.example.bimix.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Controller
@RequestMapping("/Inscription")
public class InscriptionController {
    @Autowired
    InscriptionService inscriptionService;

    @Autowired
    EmailService emailService;

    @Autowired
    ServiceService serviceService;

    @Autowired
    FonctionService fonctionService;

    @Autowired
    CatORService catORService;
    @Autowired
    PersonnelService personnelService;

    @PostMapping("/insertion_Inscription")
    public ResponseEntity<HashMap> insertion_Inscription(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String matricule = data.get("matricule");
        String email = data.get("email");
        String tel = data.get("tel");
        String pwd = data.get("pwd");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(pwd);
         int min = 100000;
        int max = 999999;
        Random random = new Random();
        int randomNumber = min + random.nextInt(max - min + 1);
        Inscription i = new Inscription();
        i.setMatricule(matricule);
        i.setTel(tel);
        i.setEmail(email);
        i.setPwd(hashedPassword);
        i.setCles(String.valueOf(randomNumber));
        try {
            Optional<Personnel> personnel = personnelService.select_Personnel_By_IM(matricule);
            if (personnel.isPresent()){
                Inscription inscription = this.inscriptionService.enregistrerInscription(i);

                emailService.sendSimpleMessage(email, "Confirmation d'inscription",
                        "Bonjour " + personnel.get().getNom() +" "+ personnel.get().getPrenom() + ",\n\nVotre inscription a été enregistrée avec succès !\n\nVotre clé d'inscription est : " + randomNumber + "\n\nMerci.");

                result.put("data", "Inscription Enregistrer");
            }
            else {
                result.put("data", "Votre Matricule n'est pas encore enregistrer");
            }
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.print("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de l'inscription");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/select_Inscription_by_Id/{id}")
    public ResponseEntity<HashMap> selectAll_Inscription_byId(@PathVariable("id") int id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            Optional<Inscription> inscription = this.inscriptionService.select_Inscription_By_id(id);
            result.put("data",inscription);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @DeleteMapping("/delete_Inscription_by_Id/{id}")
    public ResponseEntity<HashMap> delete_Inscription_by_Id(@PathVariable("id") int id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            this.inscriptionService.delete_Inscription_By_id(id);
            result.put("data","Inscription supprimer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.println("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de la suppression de l'inscription'.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/selectAll_Inscription")
    public ResponseEntity<HashMap> selectAll_Inscription() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Inscription> inscription = this.inscriptionService.selectAll_Inscription();
            result.put("data",inscription);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
