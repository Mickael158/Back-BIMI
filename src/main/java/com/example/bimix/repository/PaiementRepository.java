package com.example.bimix.repository;

import com.example.bimix.model.Paiement;
import com.example.bimix.model.Personnel;
import com.example.bimix.model.Retour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaiementRepository  extends JpaRepository<Paiement , Integer> {
    @Query(value = """
    select * from paiement where Iddepart=:Iddepart
    """,nativeQuery = true)
    Optional<Paiement> findPaiementByDepart(@Param("Iddepart") int Iddepart);

    @Modifying
    @Query(value = """
    UPDATE paiement
    SET 
        date_suivit = :dateSuivit,
        date_sortie_bon_de_caisse = :dateSortieBonDeCaisse
    WHERE idpaiement = :idPaiement
""", nativeQuery = true)
    void updatePaiement(
            @Param("dateSuivit") Date dateSuivit,
            @Param("dateSortieBonDeCaisse") Date dateSortieBonDeCaisse,
            @Param("idPaiement") Integer idPaiement
    );

    @Query(value = """
    SELECT * FROM paiement
        JOIN depart d on d.iddepart = paiement.Iddepart
            WHERE IdUtilisateur=:IdUtilisateur
        order by idpaiement desc
        limit :lim;
    """,nativeQuery = true)
    List<Paiement> findPaiementByIdUtilisateurLim(@Param("IdUtilisateur") int IdUtilisateur , @Param("lim") int lim);

    @Query(value = """
    SELECT * FROM paiement
        order by idpaiement desc\s
        limit :lim
    """,nativeQuery = true)
    List<Paiement> findPaiementLimiter(@Param("lim") int lim);
}
