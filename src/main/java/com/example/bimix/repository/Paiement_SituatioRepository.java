package com.example.bimix.repository;

import com.example.bimix.model.Paiement;
import com.example.bimix.model.Paiement_situation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Paiement_SituatioRepository extends JpaRepository<Paiement_situation, Integer> {

    @Query(value = """
    select * from paiement_situation where Idpaiement=:Idpaiement
    """,nativeQuery = true)
    List<Paiement_situation> findPaiement_situationByIdpaiement(@Param("Idpaiement") int Idpaiement);
}
