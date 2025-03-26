package com.example.bimix.repository;

import com.example.bimix.model.Depart;
import com.example.bimix.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
    Optional<Destination> findDestinationByCode_visa_passage(@Param("code_visa_destination") String code_visa_destination);

}
