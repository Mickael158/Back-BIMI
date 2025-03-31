package com.example.bimix.repository;

import com.example.bimix.model.Depart;
import com.example.bimix.model.Destination;
import com.example.bimix.model.Passage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestinationRepository extends JpaRepository<Destination , Integer> {
    @Query(value = """
    select *\s
        from destination\s
            where numero_OR=:numero_OR
    """,nativeQuery = true)
    Optional<Destination> findDestinationBynumero_OR(@Param("numero_OR") String numero_OR);

    @Query(value = """
    select *
        from destination
            where code_visa_destination=:code_visa_destination
    """,nativeQuery = true)
    Optional<Destination> findDestinationByCode_visa_destination(@Param("code_visa_destination") String code_visa_destination);

    @Query(value = """
    select * from destination where IdUtilisateur=:IdUtilisateur order by iddestination DESC LIMIT :lim
    """,nativeQuery = true)
    List<Destination> findDestinationByIdUtilisateurLim(@Param("IdUtilisateur") int IdUtilisateur , @Param("lim") int lim);

    @Query(value = """
        select * from destination order by iddestination DESC LIMIT :lim
    """,nativeQuery = true)
    List<Destination> findDestinationLimiter(@Param("lim") int lim);

}
