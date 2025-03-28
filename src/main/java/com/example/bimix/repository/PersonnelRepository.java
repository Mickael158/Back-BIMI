package com.example.bimix.repository;

import com.example.bimix.model.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {
    @Query(value = """
    SELECT * FROM personnel WHERE matricule=:matricule
    """,nativeQuery = true)
    Optional<Personnel> findPersonIM(@Param("matricule") String matricule);

    @Query(value = """
    SELECT * from personnel where matricule=:matricule and nom=:nom and prenom=:prenom and CIN=:CIN and CIN_du=:CIN_du;
    """,nativeQuery = true)
    Optional<Personnel> findPersonByImAndNonAndPrenomAndCinAndCin_du(@Param("matricule") String matricule,@Param("nom") String nom, @Param("prenom") String prenom, @Param("CIN") String CIN, @Param("CIN_du") Date CIN_du);

    @Query(value = """
    SELECT * from personnel where nom=:nom and prenom=:prenom
    """,nativeQuery = true)
    List<Personnel> findPersonByNonAndPrenom(@Param("nom") String nom, @Param("prenom") String prenom);

    @Query(value = """
    SELECT * from personnel where CIN=:CIN
    """,nativeQuery = true)
    List<Personnel> findPersonByCin(@Param("CIN") String CIN);
}
