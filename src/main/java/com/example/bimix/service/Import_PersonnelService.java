package com.example.bimix.service;

import com.example.bimix.model.Fonction_personnel;
import com.example.bimix.model.Import_Personnel;
import com.example.bimix.repository.Fonction_personnelRepository;
import com.example.bimix.repository.Import_PersonnelRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Import_PersonnelService {
    @Autowired
    private Import_PersonnelRepository import_personnelRepository;

    public void enregistrerImport_personnels(List<Import_Personnel> import_personnels) {
         this.import_personnelRepository.saveAll(import_personnels);
    }

    public void delete_Import_personnels() {
        this.import_personnelRepository.deleteAll();
    }

    public void insertDirectionImport() {
        this.import_personnelRepository.insertDirectionImport();
    }

    public void insertServiceImport() {
        this.import_personnelRepository.insertServiceImport();
    }

    public void insertFonctionImport() {
        this.import_personnelRepository.insertFonctionImport();
    }

    public void insertCatOrImport() {
        this.import_personnelRepository.insertCatOrImport();
    }

    public void insertPersonne_Not_Here() {
        this.import_personnelRepository.insertPersonne_Not_Here();
    }

    public void UpdateCatOr_personne_Import() {
        this.import_personnelRepository.UpdateCatOr_personne_Import();
    }

    public void Updatefonction_personnel_Import() {
        this.import_personnelRepository.Updatefonction_personnel_Import();
    }

    public void Updateservice_personnel_Import() {
        this.import_personnelRepository.Updateservice_personnel_Import();
    }

    public static List<String[]> getDonnee(String file){
        List<String[]> valiny  = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                valiny.add(nextLine);
            }
            return valiny;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
    public List<Import_Personnel> findAll(String file) throws IOException, ParseException {
        List<Import_Personnel> import_personnels = new ArrayList<>();
        Import_Personnel import_personnel;
        List<String[]> str_data = getDonnee(file);
        for (int i=0; i<str_data.size(); i++) {
            import_personnel = new Import_Personnel();
            import_personnel.setIM(String.valueOf(Date.valueOf(str_data.get(i)[0])));
            import_personnel.setNom(str_data.get(i)[1]);
            import_personnel.setPrenom(str_data.get(i)[2]);
            import_personnel.setCIN(str_data.get(i)[3]);
            import_personnel.setDate_CIN(str_data.get(i)[4]);
            import_personnel.setIndice(str_data.get(i)[5]);
            import_personnel.setCatOr(str_data.get(i)[6]);
            import_personnel.setCode_grade(str_data.get(i)[7]);
            import_personnel.setFonction(str_data.get(i)[8]);
            import_personnel.setTel(str_data.get(i)[9]);
            import_personnel.setDirection(str_data.get(i)[10]);
            import_personnel.setService(str_data.get(i)[11]);
            import_personnels.add(import_personnel);
        }
        return import_personnels;
    }


}
