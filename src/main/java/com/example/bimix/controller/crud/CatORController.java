package com.example.bimix.controller.crud;

import com.example.bimix.model.CatOR;
import com.example.bimix.service.CatORService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/CatOR")
public class CatORController {
    @Autowired
    CatORService catORService;

    @PostMapping("/insertion_CatOR")
    public ResponseEntity<HashMap> insertion_Direction(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String nom_catOr = data.get("nom_catOr");
        String codeGrade = data.get("codeGrade");
        String indice = data.get("indice");
        CatOR c = new CatOR();
        c.setNom(nom_catOr);
        c.setCodeGrade(codeGrade);
        c.setIndice(codeGrade);
        try {
            CatOR catOR = this.catORService.enregistrerCatOR(c);
            result.put("data", "Catégorie Org Enregistrer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.print("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de l'insertion de la catégorie Org.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/select_CatOR_by_Id/{id}")
    public ResponseEntity<HashMap> selectAll_Direction_byId(@PathVariable("id") int id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            Optional<CatOR> catOR = this.catORService.select_CatOR_By_id(id);
            result.put("data",catOR);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @DeleteMapping("/delete_CatOR_by_Id/{id}")
    public ResponseEntity<HashMap> delete_Direction_by_Id(@PathVariable("id") int id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            this.catORService.delete_CatOR_By_id(id);
            result.put("data","Catégorie Org supprimer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.println("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de la suppression de la catégorie Org.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/selectAll_CatOR")
    public ResponseEntity<HashMap> selectAll_directions() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<CatOR> catORS = this.catORService.selectAll_CatOR();
            result.put("data",catORS);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
