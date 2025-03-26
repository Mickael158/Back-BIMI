package com.example.bimix.repository;

import com.example.bimix.model.Inscription;
import com.example.bimix.model.Personnel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {

    @Modifying
    @Transactional
    @Query(value = """
    DELETE FROM inscription WHERE matricule=:matricule
    """,nativeQuery = true)
    void deleteByMatricule(@Param("matricule") String matricule);

    @Query(value = """
    select * FROM inscription WHERE matricule=:matricule
    """,nativeQuery = true)
    Optional<Inscription> selectInscriptionByIm(@Param("matricule") String matricule);
}
