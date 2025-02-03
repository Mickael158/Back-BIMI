package com.example.bimi.controller;

import com.example.bimi.model.Direction;
import com.example.bimi.model.Fonction;
import com.example.bimi.repository.FonctionRepository;
import com.example.bimi.service.DirectionService;
import com.example.bimi.service.FonctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Fonction")
public class FonctionController {
    @Autowired
    FonctionService fonctionService;

    @PostMapping("/insertion_Fonction")
    public ResponseEntity<HashMap> insertion_Fonction(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String nom_Fonction = data.get("nom_Fonction");
        Fonction f = new Fonction();
        f.setNom(nom_Fonction);
        try {
            Fonction fonction = this.fonctionService.enregistrerFonction(f);
            result.put("data", "Fonction Enregistrer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.print("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de l'insertion de la direction.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/select_Fonction_by_Id/{id}")
    public ResponseEntity<HashMap> selectAll_Fonction_byId(@PathVariable("id") int id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            Optional<Fonction> fonction = this.fonctionService.select_Fonction_By_id(id);
            result.put("data",fonction);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @DeleteMapping("/delete_Fonction_by_Id/{id}")
    public ResponseEntity<HashMap> delete_Fonction_by_Id(@PathVariable("id") int id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            this.fonctionService.delete_Fonction_By_id(id);
            result.put("data","Fonction supprimer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.println("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de la suppression de la Fonction.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/selectAll_Fonction")
    public ResponseEntity<HashMap> selectAll_Fonction() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Fonction> fonctions = this.fonctionService.selectAll_Fonction();
            result.put("data",fonctions);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
