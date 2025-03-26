package com.example.bimix.repository;

import com.example.bimix.model.Itineraire;
import com.example.bimix.model.Passage;
import com.example.bimix.model.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItineraireRepository extends JpaRepository<Itineraire , Integer> {
    @Query(value = """
    SELECT i.*
        FROM itineraire i
             LEFT JOIN passage p ON i.idItineraire = p.idItineraire
             JOIN depart d ON i.iddepart = d.iddepart
        WHERE p.code_visa_passage IS NULL
        AND d.numero_OR =:numero_OR
        AND i.numero < (
            select MAX(ip.numero) from itineraire ip WHERE ip.iddepart = d.iddepart
            )
    ORDER BY i.numero
        LIMIT 1;
    """,nativeQuery = true)
    Optional<Itineraire> findItineraireNext(@Param("numero_OR") String numero_OR);

    @Query(value = """
      SELECT i.*
          FROM itineraire i
               JOIN depart d ON i.iddepart = d.iddepart
          WHERE d.numero_OR =:numero_OR
          AND i.numero = (
              select MAX(ip.numero) from itineraire ip WHERE ip.iddepart = d.iddepart);
    """,nativeQuery = true)
    Optional<Itineraire> findItineraireFin(@Param("numero_OR") String numero_OR);
}
