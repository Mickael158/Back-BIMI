package com.example.bimix.controller.Personnel;

import com.example.bimix.model.*;
import com.example.bimix.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Personnel")
public class PersonnelController {
    @Autowired
    PersonnelService personnelService;

    @Autowired
    FonctionService fonctionService;

    @Autowired
    CatORService catORService;

    @Autowired
    ServiceService serviceService;

    @Autowired
    Fonction_personnelService fonction_personnelService;

    @Autowired
    CatOr_personneService catOr_personneService;

    @Autowired
    Service_PersonnelService service_personnelService;

    @Transactional
    @PostMapping("/insertion_Personnel")
    public ResponseEntity<HashMap> insertion_Personnel(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String matricule = data.get("matricule");
        String nom = data.get("nom");
        String prenom = data.get("prenom");
        String CIN = data.get("CIN");
        String CIN_du = data.get("CIN_du");
        String email = data.get("email");
        String tel = data.get("tel");
        String idcatOR = data.get("idcatOR");
        String idfonction = data.get("idfonction");
        String idservice = data.get("idservice");
        Optional<CatOR> catOR = this.catORService.select_CatOR_By_id(Integer.parseInt(idcatOR));
        Optional<Fonction> fonction = this.fonctionService.select_Fonction_By_id(Integer.parseInt(idfonction));
        Optional<ServiceM> idserviceM = this.serviceService.select_ServiceM_By_id(Integer.parseInt(idservice));
        Personnel p = new Personnel();
        p.setMatricule(matricule);
        p.setNom(nom);
        p.setPrenom(prenom);
        p.setCIN(CIN);
        p.setCIN_du(Date.valueOf(CIN_du));
        p.setEmail(email);
        p.setTel(tel);
        CatOr_personne cp = new CatOr_personne();
        cp.setIdPersonnel(p);
        cp.setDates(new Date(new java.util.Date().getTime()));
        cp.setIdCatOr(catOR.get());
        Fonction_personnel fp = new Fonction_personnel();
        fp.setDates(new Date(new java.util.Date().getTime()));
        fp.setIdFonction(fonction.get());
        fp.setIdPersonnel(p);
        Service_Personnel sp = new Service_Personnel();
        sp.setDates(new Date(new java.util.Date().getTime()));
        sp.setIdPersonnel(p);
        sp.setIdService(idserviceM.get());
        try {
            Personnel personnel = this.personnelService.enregistrerPersonnel(p);
            CatOr_personne catOr_personne = this.catOr_personneService.enregistrerCatOr_personne(cp);
            Fonction_personnel fonction_personnel = this.fonction_personnelService.enregistrerFonction_personnel(fp);
            Service_Personnel service_personnel = this.service_personnelService.enregistrerService_Personnel(sp);
            result.put("data", "Personnel enregistrer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.print("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de l'enregistrer du personnel");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/select_Personnel_by_Id/{id}")
    public ResponseEntity<HashMap> selectAll_Inscription_byId(@PathVariable("id") int id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            Optional<Personnel> personnel = this.personnelService.select_Personnel_By_id(id);
            result.put("data",personnel);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @DeleteMapping("/delete_Personnel_by_Id/{id}")
    public ResponseEntity<HashMap> delete_Inscription_by_Id(@PathVariable("id") int id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            this.personnelService.delete_Personnel_By_id(id);
            result.put("data","Personnel supprimer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.println("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de la suppression du Personnel'.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/selectAll_Personnel")
    public ResponseEntity<HashMap> selectAll_Inscription() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Personnel> personnels = this.personnelService.selectAll_Inscription();
            result.put("data",personnels);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
