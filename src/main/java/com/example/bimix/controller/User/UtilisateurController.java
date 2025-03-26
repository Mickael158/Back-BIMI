package com.example.bimix.controller.User;

import com.example.bimix.model.Personnel;
import com.example.bimix.model.Role;
import com.example.bimix.model.Utilisateur;
import com.example.bimix.service.PersonnelService;
import com.example.bimix.service.RoleService;
import com.example.bimix.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Utilisateur")
public class UtilisateurController {
    @Autowired
    PersonnelService personnelService;

    @Autowired
    UtilisateurService utilisateurService;

    @Autowired
    RoleService roleService;

    @PostMapping("/insertion_Utilisateur")
    public ResponseEntity<HashMap> insertion_Personnel(@RequestBody HashMap<String , String> data) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        String idPersonnel = data.get("idPersonnel");
        String idRole = data.get("idRole");
        String pwd = data.get("pwd");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(pwd);
        Optional<Role> role = this.roleService.select_Role_By_id(Integer.parseInt(idRole));
        Optional<Personnel> personnel = this.personnelService.select_Personnel_By_id(Integer.parseInt(idPersonnel));
        Utilisateur u = new Utilisateur();
        u.setIdPersonnel(personnel.get());
        u.setIdRole(role.get());
        u.setPwd(hashedPassword);
        try {
            Utilisateur utilisateur = this.utilisateurService.enregistrerUtilisateur(u) ;
            result.put("data", "Personnel enregistrer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.print("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de l'enregistrer du personnel");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/select_Utilisateur_by_Id/{id}")
    public ResponseEntity<HashMap> selectAll_Inscription_byId(@PathVariable("id") int id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            Optional<Utilisateur> utilisateur = this.utilisateurService.select_Utilisateur_By_id(id);
            result.put("data",utilisateur);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @DeleteMapping("/delete_Utilisateur_by_Id/{id}")
    public ResponseEntity<HashMap> delete_Inscription_by_Id(@PathVariable("id") int id) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            this.utilisateurService.delete_Utilisateur_By_id(id);
            result.put("data","Personnel supprimer");
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            System.out.println("Erreur" + e.getMessage());
            result.put("Erreur" , "Une erreur s'est produite lors de la suppression du Personnel'.");
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
    @GetMapping("/selectAll_Utilisateur")
    public ResponseEntity<HashMap> selectAll_Inscription() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<Utilisateur> utilisateurs = this.utilisateurService.selectAll_Utilisateur();
            result.put("data",utilisateurs);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (Exception e) {
            result.put("Erreur" , e.getMessage());
        }
        return new ResponseEntity<>(result , HttpStatus.OK);
    }
}
