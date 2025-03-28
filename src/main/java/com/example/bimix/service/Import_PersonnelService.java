package com.example.bimix.service;

import com.example.bimix.model.*;
import com.example.bimix.repository.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class Import_PersonnelService {
    @Autowired
    private Import_PersonnelRepository import_personnelRepository;

    @Autowired
    CatORService catORService;


    @Autowired
    FonctionService fonctionService;

    @Autowired
    ServiceService serviceService;

    @Autowired
    private CatOr_personneRepository catOr_personneRepository;

    @Autowired
    private Fonction_personnelRepository fonction_personnelRepository;

    @Autowired
    private Service_PersonnelRepository servicePersonnelRepository;
    @Autowired
    private PersonnelRepository personnelRepository;


    public void enregistrerImport_personnels(List<Import_Personnel> import_personnels) {
         this.import_personnelRepository.saveAll(import_personnels);
    }

    public void enregistrerPersonneNotHereExcate(List<Personne_import> selectPersonneNotHereExcate) {
        for (Personne_import personne_import : selectPersonneNotHereExcate){
            Optional<Import_Personnel> import_personnel = selectImport_PersonnelByImAndNomAndPrenomAndCinAndDateDu(personne_import.getMatricule(), personne_import.getNom(), personne_import.getPrenom(), personne_import.getCIN(), personne_import.getCIN_du());
            Personnel p = new Personnel();
            p.setDates(new Date(new java.util.Date().getTime()));
            p.setStatus(true);
            p.setMatricule(import_personnel.get().getIM());
            p.setNom(import_personnel.get().getNom());
            p.setPrenom(import_personnel.get().getPrenom());
            p.setCIN(import_personnel.get().getCIN());
            p.setCIN_du(import_personnel.get().getDate_CIN());
            p.setEmail("");
            p.setTel("");
            Optional<CatOR> catOR_import = this.catORService.findCatOrByNomAndCodeGradeAndIndice(import_personnel.get().getCatOr() , import_personnel.get().getCode_grade() , import_personnel.get().getIndice());
            CatOr_personne cp = new CatOr_personne();
            cp.setIdPersonnel(p);
            cp.setDates(new Date(new java.util.Date().getTime()));
            cp.setIdCatOr(catOR_import.get());
            Optional<Fonction> fonction_import = this.fonctionService.findFonctionByNom(import_personnel.get().getFonction());
            Fonction_personnel fp = new Fonction_personnel();
            fp.setDates(new Date(new java.util.Date().getTime()));
            fp.setIdFonction(fonction_import.get());
            fp.setIdPersonnel(p);
            Optional<ServiceM> service_import = this.serviceService.findServiceMByServiceAndDirection(import_personnel.get().getService() , import_personnel.get().getDirection());
            Service_Personnel sp = new Service_Personnel();
            sp.setDates(new Date(new java.util.Date().getTime()));
            sp.setIdPersonnel(p);
            sp.setIdService(service_import.get());
            if (catOR_import.isPresent() && fonction_import.isPresent() && service_import.isPresent()){
                this.personnelRepository.save(p);
                this.catOr_personneRepository.save(cp);
                this.fonction_personnelRepository.save(fp);
                this.servicePersonnelRepository.save(sp);
            }
        }
    }

    public void enregistrerPersonneHereExcate(List<Personne_import> selectPersonneNotHereExcate) {
        for (Personne_import personne_import : selectPersonneNotHereExcate){
            Optional<Personnel> personnel = this.personnelRepository.findPersonByImAndNonAndPrenomAndCinAndCin_du(personne_import.getMatricule(), personne_import.getNom(), personne_import.getPrenom(), personne_import.getCIN(), personne_import.getCIN_du());
            Optional<Import_Personnel> import_personnel = selectImport_PersonnelByImAndNomAndPrenomAndCinAndDateDu(personne_import.getMatricule(), personne_import.getNom(), personne_import.getPrenom(), personne_import.getCIN(), personne_import.getCIN_du());
            Optional<CatOR> catOR_import = this.catORService.findCatOrByNomAndCodeGradeAndIndice(import_personnel.get().getCatOr() , import_personnel.get().getCode_grade() , import_personnel.get().getIndice());
            CatOr_personne cp = new CatOr_personne();
            cp.setIdPersonnel(personnel.get());
            cp.setDates(new Date(new java.util.Date().getTime()));
            cp.setIdCatOr(catOR_import.get());
            Optional<Fonction> fonction_import = this.fonctionService.findFonctionByNom(import_personnel.get().getFonction());
            Fonction_personnel fp = new Fonction_personnel();
            fp.setDates(new Date(new java.util.Date().getTime()));
            fp.setIdFonction(fonction_import.get());
            fp.setIdPersonnel(personnel.get());
            Optional<ServiceM> service_import = this.serviceService.findServiceMByServiceAndDirection(import_personnel.get().getService() , import_personnel.get().getDirection());
            Service_Personnel sp = new Service_Personnel();
            sp.setDates(new Date(new java.util.Date().getTime()));
            sp.setIdPersonnel(personnel.get());
            sp.setIdService(service_import.get());
            if (catOR_import.isPresent() && fonction_import.isPresent() && service_import.isPresent()){
                this.catOr_personneRepository.save(cp);
                this.fonction_personnelRepository.save(fp);
                this.servicePersonnelRepository.save(sp);
            }
        }
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

    public List<Personne_import> selectPersonneImporter() {
        return this.import_personnelRepository.selectPersonneImporter();
    }
    public Optional<Import_Personnel> selectImport_PersonnelByImAndNomAndPrenomAndCinAndDateDu(String Im , String nom ,String prenom , String Cin , Date date_du) {
        return this.import_personnelRepository.selectImport_PersonnelByImAndNomAndPrenomAndCinAndDateDu(Im , nom , prenom , Cin , date_du);
    }
    public List<Personne_import> selectPersonneNotHereExcate(List<Personne_import> personne_imports) {
        List<Personne_import> personneImports = new ArrayList<>();
        for (Personne_import personne_import : personne_imports){
            Optional<Personnel> personnelByNonAndPrenomAndCinAndCin_du = this.personnelRepository.findPersonByImAndNonAndPrenomAndCinAndCin_du(personne_import.getMatricule(),personne_import.getNom() , personne_import.getPrenom() , personne_import.getCIN() , personne_import.getCIN_du());
            if (personnelByNonAndPrenomAndCinAndCin_du.isEmpty()){
                personneImports.add(personne_import);
            }
        }
        return personneImports;
    }

    public List<Personne_import> selectPersonneHereExacte(List<Personne_import> personne_imports) {
        List<Personne_import> personneImports = new ArrayList<>();
        for (Personne_import personne_import : personne_imports){
                Optional<Personnel> personnelByNonAndPrenomAndCinAndCin_du = this.personnelRepository.findPersonByImAndNonAndPrenomAndCinAndCin_du(personne_import.getMatricule(),personne_import.getNom() , personne_import.getPrenom() , personne_import.getCIN() , personne_import.getCIN_du());
                if (personnelByNonAndPrenomAndCinAndCin_du.isPresent()){
                    personneImports.add(personne_import);
                }
        }
        return personneImports;
    }

    public List<Personne_import> selectPersonneHaveImExiste(List<Personne_import> personne_imports) {
        List<Personne_import> personneImports = new ArrayList<>();
        for (Personne_import personne_import : personne_imports){
            Optional<Personnel> personnelByIm = this.personnelRepository.findPersonIM(personne_import.getMatricule());
            if(personnelByIm.isPresent()){
                personneImports.add(personne_import);
            }
        }
        return personneImports;
    }
    public List<Personne_import> selectPersonneHaveNomAndPrenomExiste(List<Personne_import> personne_imports) {
        List<Personne_import> personneImports = new ArrayList<>();
        for (Personne_import personne_import : personne_imports){
            List<Personnel> personnelByNonAndPrenom = this.personnelRepository.findPersonByNonAndPrenom(personne_import.getNom() , personne_import.getPrenom());
            if(personnelByNonAndPrenom.size() !=0 ){
                personneImports.add(personne_import);
            }
        }
        return personneImports;
    }
    public List<Personne_import> selectPersonneHaveCinExiste(List<Personne_import> personne_imports) {
        List<Personne_import> personneImports = new ArrayList<>();
        for (Personne_import personne_import : personne_imports){
            List<Personnel> personnelByCin = this.personnelRepository.findPersonByCin(personne_import.getCIN());
            if(personnelByCin.size() !=0 ){
                personneImports.add(personne_import);
            }
        }
        return personneImports;
    }

//    public void insertPersonne_Not_Here() {
//        this.import_personnelRepository.insertPersonne_Not_Here();
//    }
//
//    public void UpdateCatOr_personne_Import() {
//        this.import_personnelRepository.UpdateCatOr_personne_Import();
//    }
//
//    public void Updatefonction_personnel_Import() {
//        this.import_personnelRepository.Updatefonction_personnel_Import();
//    }
//
//    public void Updateservice_personnel_Import() {
//        this.import_personnelRepository.Updateservice_personnel_Import();
//    }

//    public static List<String[]> getDonnee(String file){
//        List<String[]> valiny  = new ArrayList<>();
//        try (CSVReader reader = new CSVReader(new FileReader(file))) {
//            String[] nextLine;
//            reader.readNext();
//            while ((nextLine = reader.readNext()) != null) {
//                valiny.add(nextLine);
//            }
//            return valiny;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//    public List<Import_Personnel> findAll(String file) throws IOException, ParseException {
//        List<Import_Personnel> import_personnels = new ArrayList<>();
//        Import_Personnel import_personnel;
//        List<String[]> str_data = getDonnee("D:/Projet/BIMI/"+file);
//        for (int i=0; i<str_data.size(); i++) {
//            import_personnel = new Import_Personnel();
//            import_personnel.setIM(String.valueOf(Date.valueOf(str_data.get(i)[0])));
//            import_personnel.setNom(str_data.get(i)[1]);
//            import_personnel.setPrenom(str_data.get(i)[2]);
//            import_personnel.setCIN(str_data.get(i)[3]);
//            import_personnel.setDate_CIN(str_data.get(i)[4]);
//            import_personnel.setIndice(str_data.get(i)[5]);
//            import_personnel.setCatOr(str_data.get(i)[6]);
//            import_personnel.setCode_grade(str_data.get(i)[7]);
//            import_personnel.setFonction(str_data.get(i)[8]);
//            import_personnel.setTel(str_data.get(i)[9]);
//            import_personnel.setDirection(str_data.get(i)[10]);
//            import_personnel.setService(str_data.get(i)[11]);
//            import_personnels.add(import_personnel);
//        }
//        return import_personnels;
//    }

    public static List<String[]> getDonnee(String file) {
        List<String[]> valiny = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Ignorer la première ligne (header)
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                int nbColumns = row.getLastCellNum();
                String[] rowData = new String[nbColumns];

                for (int i = 0; i < nbColumns; i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowData[i] = getCellValueAsString(cell);
                }
                valiny.add(rowData);
            }
            return valiny;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue().toString();
                }
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    public List<Import_Personnel> findAll(String file) throws IOException, ParseException {
        List<Import_Personnel> import_personnels = new ArrayList<>();
        Import_Personnel import_personnel;
        List<String[]> str_data = getDonnee("D:/Projet/BIMI/" + file);

        if (str_data != null) {
            for (String[] row : str_data) {
                import_personnel = new Import_Personnel();
                import_personnel.setIM(row[0]);
                import_personnel.setNom(row[1]);
                import_personnel.setPrenom(row[2]);
                import_personnel.setCIN(row[3]);
                import_personnel.setDate_CIN(convertStringToSqlDate(row[4]));
                import_personnel.setIndice(row[5]);
                import_personnel.setCatOr(row[6]);
                import_personnel.setCode_grade(row[7]);
                import_personnel.setFonction(row[8]);
                import_personnel.setTel(row[9]);
                import_personnel.setDirection(row[10]);
                import_personnel.setService(row[11]);
                import_personnels.add(import_personnel);
            }
        }
        return import_personnels;
    }
    public static java.sql.Date convertStringToSqlDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date utilDate = null;
        try {
            utilDate = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace(); // Gérer l'exception selon vos besoins
        }

        // Convertir java.util.Date en java.sql.Date
        return (utilDate != null) ? new java.sql.Date(utilDate.getTime()) : null;
    }

}
