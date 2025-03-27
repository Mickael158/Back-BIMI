package com.example.bimix.repository;

import com.example.bimix.model.Depart;
import com.example.bimix.model.Retour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RetourRepository extends JpaRepository<Retour , Integer> {

    @Query(value = """
    select *\s
        from retour\s
            where numero_OR=:numero_OR
    """,nativeQuery = true)
    Optional<Retour> findRetourBynumero_OR(@Param("numero_OR") String numero_OR);

    @Query(value = """
    select * from retour where code_Visa_fin=:code_Visa_fin
    """,nativeQuery = true)
    Optional<Retour> findRetourByCode_visa_fin(@Param("code_Visa_fin") String code_Visa_fin);

}
