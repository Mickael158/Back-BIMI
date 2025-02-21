package com.example.bimix.controller.crud;

import com.example.bimix.model.CatOR;
import com.example.bimix.model.Transport;
import com.example.bimix.service.CatORService;
import com.example.bimix.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Transport")
public class TransportController {
    @Autowired
    TransportService transportService;

    @PostMapping("/insertion_idTransport")
    public ResponseEntity<HashMap> insertion_Transport(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String nom_Transport = data.get("nom_Transport");
        Transport t = new Transport();
        t.setNom(nom_Transport);
        try {
            Transport transport = this.transportService.enregistrerTransport(t);
            result.put("data", "Transport Enregistrer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.print("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de l'insertion du Transport.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/select_Transport_by_Id/{id}")
    public ResponseEntity<HashMap> selectAll_Transport_byId(@PathVariable("id") int id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            Optional<Transport> transport = this.transportService.select_Transport_By_id(id);
            result.put("data",transport);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @DeleteMapping("/delete_Transport_by_Id/{id}")
    public ResponseEntity<HashMap> delete_Transport_by_Id(@PathVariable("id") int id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            this.transportService.delete_Transport_By_id(id);
            result.put("data","Transport supprimer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.println("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de la suppression de la Transport.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/selectAll_Transport")
    public ResponseEntity<HashMap> selectAll_Transport() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Transport> transports = this.transportService.selectAll_Transport();
            result.put("data",transports);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
