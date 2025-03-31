package com.example.bimix.repository;

import com.example.bimix.model.Fonction_personnel;
import com.example.bimix.model.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface Fonction_personnelRepository extends JpaRepository<Fonction_personnel, Integer> {
    @Query(value = """
    SELECT *
    FROM fonction_personnel
    WHERE idPersonnel =:idPersonnel
      AND dates = (SELECT MAX(dates) FROM fonction_personnel WHERE idPersonnel =:idPersonnel);
    """,nativeQuery = true)
    Optional<Fonction_personnel> findFonction_personnelMaxByIdPersonnel(@Param("idPersonnel") int idPersonnel);

    @Query(value = """
    select * from fonction_personnel where idPersonnel=:idPersonnel AND idFonction=:idFonction
    """,nativeQuery = true)
    Optional<Fonction_personnel> findFonction_personnelByIdPersonnelAAndIdFonction(@Param("idPersonnel") int idPersonnel, @Param("idFonction") int idFonction);
}
