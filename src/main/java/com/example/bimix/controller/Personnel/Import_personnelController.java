package com.example.bimix.controller.Personnel;


import com.example.bimix.configuration.JWTManager;
import com.example.bimix.model.*;
import com.example.bimix.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;
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
    CatORService catORService;

    @Autowired
    FonctionService fonctionService;
    @Autowired
    ServiceService serviceService;

    @Autowired
    JWTManager jwtManager;

    @Transactional
    @PostMapping("/import")
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
            import_personnelService.delete_Import_personnels();
            import_personnelService.enregistrerImport_personnels(import_personnelList);
            import_personnelService.insertDirectionImport();
            import_personnelService.insertServiceImport();
            import_personnelService.insertFonctionImport();
            import_personnelService.insertCatOrImport();
            List<Personne_import> personneImports=  this.import_personnelService.selectPersonneImporter();
//            List<Personne_import> selectPersonneNotHereExcate=  this.import_personnelService.selectPersonneNotHereExcate(personneImports);
//            List<Personne_import> selectPersonneHereExacte=  this.import_personnelService.selectPersonneHereExacte(personneImports);
//            List<Personne_import> selectPersonneHaveImExiste=  this.import_personnelService.selectPersonneHaveImExiste(personneImports);
//            List<Personne_import> selectPersonneHaveNomAndPrenomExiste=  this.import_personnelService.selectPersonneHaveNomAndPrenomExiste(personneImports);
//            List<Personne_import> selectPersonneHaveCinExiste=  this.import_personnelService.selectPersonneHaveCinExiste(personneImports);
//            import_personnelService.enregistrerPersonneNotHereExcate(selectPersonneNotHereExcate);
//            import_personnelService.enregistrerPersonneHereExcate(selectPersonneHereExacte);
            result.put("data","import des personnels Terminer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
