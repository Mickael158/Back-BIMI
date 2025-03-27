package com.example.bimix.repository;

import com.example.bimix.model.Modif_paiement;
import com.example.bimix.model.Paiement_situation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Modif_PaiementRepository extends JpaRepository<Modif_paiement, Integer> {
    @Query(value = """
    select * from modif_paiment where Idpaiement=:Idpaiement
    """,nativeQuery = true)
    Optional<Modif_paiement> findModif_paiementByIdpaiement(@Param("Idpaiement") int Idpaiement);
}
