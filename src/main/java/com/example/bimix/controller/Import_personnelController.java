package com.example.bimix.controller;


import com.example.bimix.configuration.JWTManager;
import com.example.bimix.model.Import_Personnel;
import com.example.bimix.model.Utilisateur;
import com.example.bimix.service.DepartSercvice;
import com.example.bimix.service.Import_PersonnelService;
import com.example.bimix.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Import_personnel")
public class Import_personnelController {

    @Autowired
    Import_PersonnelService import_personnelService;

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    JWTManager jwtManager;

    @PostMapping("/Import_personnel")
    public ResponseEntity<HashMap> importation(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String file = data.get("file");
        String token = data.get("token");
        String idUtilisateur = jwtManager.getClaim(String.valueOf(token), "id");
        Optional<Utilisateur> utilisateur = utilisateurService.select_Utilisateur_By_id(Integer.parseInt(idUtilisateur));
        List<Import_Personnel> import_personnelList = new ArrayList<>();
        List<Import_Personnel> import_personnels = import_personnelService.findAll(file);
        for (Import_Personnel import_personnel : import_personnels){
            import_personnel.setUtilisateur(utilisateur.get());
            import_personnelList.add(import_personnel);
        }
        try {
            import_personnelService.enregistrerImport_personnels(import_personnelList);
            import_personnelService.insertDirectionImport();
            import_personnelService.insertServiceImport();
            import_personnelService.insertFonctionImport();
            import_personnelService.insertCatOrImport();
            import_personnelService.insertPersonne_Not_Here();
            import_personnelService.UpdateCatOr_personne_Import();
            import_personnelService.Updatefonction_personnel_Import();
            import_personnelService.Updateservice_personnel_Import();
            result.put("data","import des personnels Terminer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
