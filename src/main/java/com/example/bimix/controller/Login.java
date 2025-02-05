package com.example.bimix.controller;

import com.example.bimix.configuration.JWTManager;
import com.example.bimix.model.Utilisateur;
import com.example.bimix.service.FonctionService;
import com.example.bimix.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/Login")
public class Login {
    @Autowired
    LoginService loginService;

    @Autowired
    FonctionService fonctionService;

    @Autowired
    JWTManager jwtManager;

    @PostMapping("/checking")
    public ResponseEntity<HashMap> checking(@RequestBody Map<String, String> credentials) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String matricule = credentials.get("matricule");
        String pswd = credentials.get("pswd");
        try {

            Utilisateur utilisateur = this.loginService.login(matricule);
            if (utilisateur != null) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                if (passwordEncoder.matches(pswd, utilisateur.getPwd())) {
                    result.put("data", jwtManager.generateToken(utilisateur));

                    return new ResponseEntity<>(result, HttpStatus.OK);
                } else {
                    result.put("Erreur", "Mot de passe incorrect.");
                    return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
                }
            }
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
