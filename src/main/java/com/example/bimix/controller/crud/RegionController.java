package com.example.bimix.controller.crud;

import com.example.bimix.model.CatOR;
import com.example.bimix.model.Region;
import com.example.bimix.service.CatORService;
import com.example.bimix.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Region")
public class RegionController {
    @Autowired
    RegionService regionService;

    @PostMapping("/insertion_Region")
    public ResponseEntity<HashMap> insertion_Region(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String nom_Region = data.get("nom_Region");
        Region r = new Region();
        r.setNom(nom_Region);
        try {
            Region region = this.regionService.enregistrerRegion(r);
            result.put("data", "Région Enregistrer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.print("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de l'insertion de la région.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/select_Region_by_Id/{id}")
    public ResponseEntity<HashMap> selectAll_Region_byId(@PathVariable("id") int id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            Optional<Region> region = this.regionService.select_Region_By_id(id);
            result.put("data",region);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @DeleteMapping("/delete_Region_by_Id/{id}")
    public ResponseEntity<HashMap> delete_Region_by_Id(@PathVariable("id") int id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            this.regionService.delete_Region_By_id(id);
            result.put("data","Régionsupprimer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.println("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de la suppression de la région.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/selectAll_Region")
    public ResponseEntity<HashMap> selectAll_directions() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Region> region = this.regionService.selectAll_Region();
            result.put("data",region);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
