package com.example.bimi.controller;

import com.example.bimi.model.Direction;
import com.example.bimi.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Direction")
public class DirectionController {
    @Autowired
    DirectionService directionService;

    @PostMapping("/insertion_Direction")
    public ResponseEntity<HashMap> insertion_Direction(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String nom_Direction = data.get("nom_Direction");
        Direction d = new Direction();
        d.setNom(nom_Direction);
        try {
            Direction direction = this.directionService.enregistrerDirection(d);
            result.put("data", "Direction Enregistrer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.print("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de l'insertion de la direction.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/select_Direction_by_Id/{id}")
    public ResponseEntity<HashMap> selectAll_Direction_byId(@PathVariable("id") int id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            Optional<Direction> directions = this.directionService.select_Direction_By_id(id);
            result.put("data",directions);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @DeleteMapping("/delete_Direction_by_Id/{id}")
    public ResponseEntity<HashMap> delete_Direction_by_Id(@PathVariable("id") int id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            this.directionService.delete_Direction_By_id(id);
            result.put("data","Direction supprimer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.println("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de la suppression de la direction.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/selectAll_directions")
    public ResponseEntity<HashMap> selectAll_directions() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Direction> directions = this.directionService.selectAll_Direction();
            result.put("data",directions);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
