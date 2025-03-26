package com.example.bimix.controller.User;

import com.example.bimix.configuration.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/Token")
public class TokenController {
    @Autowired
    JWTManager jwtManager;

    @PostMapping("/getRole")
    public ResponseEntity<HashMap> getRole(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String utilisateur = data.get("utilisateur");
        try {
            result.put("data",Integer.parseInt(jwtManager.getClaim(String.valueOf(utilisateur), "role")));
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @PostMapping("/getPersonne_Im")
    public ResponseEntity<HashMap> getPersonne_Im(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String utilisateur = data.get("utilisateur");
        try {
            result.put("data",jwtManager.getClaim(String.valueOf(utilisateur), "im"));
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }

    @PostMapping("/getPersonne_Nom_Prenom")
    public ResponseEntity<HashMap> getPersonne_Nom_prenom(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String utilisateur = data.get("utilisateur");
        try {
            String nom_prenom = jwtManager.getClaim(String.valueOf(utilisateur), "nom") +" "+ jwtManager.getClaim(String.valueOf(utilisateur), "prenom");
            result.put("data", nom_prenom);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
