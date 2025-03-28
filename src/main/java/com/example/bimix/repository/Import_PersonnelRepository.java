package com.example.bimix.repository;

import com.example.bimix.model.Depart;
import com.example.bimix.model.Import_Personnel;
import com.example.bimix.model.Personne_import;
import com.example.bimix.model.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface Import_PersonnelRepository extends JpaRepository<Import_Personnel ,Integer> {



//
//    @Modifying
//    @Query(value = """
//    INSERT INTO personnel ( matricule, nom, prenom, CIN, CIN_du, email, tel) (
//        SELECT DISTINCT personnel.matricule , personnel.nom , personnel.prenom , personnel.CIN , personnel.CIN_du , personnel.email , personnel.tel
//        from import_personnel
//                 left join personnel
//                           on personnel.matricule = import_personnel.IM
//                               AND personnel.nom = import_personnel.nom
//                               AND personnel.prenom = import_personnel.prenom
//                               AND personnel.CIN = import_personnel.CIN
//                               AND personnel.CIN_du = import_personnel.date_CIN
//        where personnel.matricule IS NULL
//          AND personnel.nom IS NULL
//          AND personnel.prenom IS NULL
//          AND personnel.CIN_du IS NULL
//          AND personnel.CIN IS NULL
//    );
//    """,nativeQuery = true)
//    void insertPersonne_Not_Here();
//
//    @Modifying
//    @Query(value = """
//    INSERT INTO CatOr_personne (dates, idcator, idpersonnel) (
//        select DISTINCT NOW() , c.IdCatOr , p.idPersonnel
//        FROM import_personnel
//                 join personnel p on import_personnel.IM=p.matricule
//                 join CatOR c
//                      on c.nom=import_personnel.catOr
//                          AND c.codeGrade=import_personnel.code_grade
//                          AND c.indice=import_personnel.indice
//    ) ;
//    """,nativeQuery = true)
//    void UpdateCatOr_personne_Import();
//
//    @Modifying
//    @Query(value = """
//    INSERT INTO fonction_personnel (dates, idFonction, idPersonnel)  (
//        select DISTINCT NOW() , f.idFonction , p.idPersonnel
//        FROM import_personnel
//                 join personnel p on import_personnel.IM=p.matricule
//                 join fonction f
//                      on f.nom=import_personnel.fonction
//    ) ;
//    """,nativeQuery = true)
//    void Updatefonction_personnel_Import();
//
//
//    @Modifying
//    @Query(value = """
//    INSERT INTO service_personnel (dates, idService, idPersonnel)  (
//        select DISTINCT NOW() , s.idService , p.idPersonnel
//        FROM import_personnel
//                 join personnel p on import_personnel.IM=p.matricule
//                 join service s on s.nom=import_personnel.service
//    ) ;
//    """,nativeQuery = true)
//    void Updateservice_personnel_Import();
    @Modifying
    @Query(value = """
    insert into direction (nom)  (
       SELECT DISTINCT import_personnel.direction from import_personnel
            left join direction on direction.nom= import_personnel.direction
       WHERE direction.nom IS NULL
                                       );
    """,nativeQuery = true)
    void insertDirectionImport();
    @Modifying
    @Query(value = """
    INSERT INTO service (nom , idDirection) (
        SELECT DISTINCT import_personnel.service , d.idDirection   from import_personnel
            left join service on service.nom= import_personnel.service
            left join direction d on d.nom=import_personnel.direction
        WHERE service.nom IS NULL
    );
    """,nativeQuery = true)
    void insertServiceImport();


    @Modifying
    @Query(value = """
    insert into fonction (nom) (
        SELECT DISTINCT import_personnel.fonction from import_personnel
            left join fonction on fonction.nom= import_personnel.fonction
        WHERE fonction.nom IS NULL
    );
    """,nativeQuery = true)
    void insertFonctionImport();

    @Modifying
    @Query(value = """
    insert into CatOR ( nom, codeGrade, indice) (
        SELECT DISTINCT ip.catOr, ip.code_grade, ip.indice
        FROM import_personnel ip
                 LEFT JOIN CatOR c ON ip.catOr = c.nom
            AND ip.code_grade = c.codeGrade
            AND ip.indice = c.indice
        WHERE c.nom IS NULL OR c.codeGrade IS NULL OR c.indice IS NULL
    );
    """,nativeQuery = true)
    void insertCatOrImport();

    @Query(value = """
    SELECT DISTINCT IM , nom , prenom , CIN , date_CIN from import_personnel;
    """,nativeQuery = true)
    List<Personne_import> selectPersonneImporter();

    @Query(value = """
    select DISTINCT *\s
        from import_personnel\s
            where IM=:IM AND nom=:nom AND prenom=:prenom AND CIN=:CIN AND date_CIN=:date_CIN
    """,nativeQuery = true)
    Optional<Import_Personnel> selectImport_PersonnelByImAndNomAndPrenomAndCinAndDateDu(@Param("IM") String IM, @Param("nom") String nom, @Param("prenom") String prenom, @Param("CIN") String CIN, @Param("CIN") Date date_CIN);
}
