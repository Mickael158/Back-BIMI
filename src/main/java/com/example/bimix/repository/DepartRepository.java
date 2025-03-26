package com.example.bimix.repository;

import com.example.bimix.model.Depart;
import com.example.bimix.model.Itineraire;
import com.example.bimix.model.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartRepository extends JpaRepository<Depart , Integer> {

    @Query(value = """
    select *\s
        from depart\s
            where numero_OR=:numero_OR
    """,nativeQuery = true)
    Optional<Depart> findDepartBynumero_OR(@Param("numero_OR") String numero_OR);

    @Query(value = """
    select *
        from depart
            where code_Visa_depart=:code_Visa_depart
    """,nativeQuery = true)
    Optional<Depart> findDepartBycode_Visa_depart(@Param("code_Visa_depart") String code_Visa_depart);

    @Query(value = """
    select *
        from depart
            where bordereau=:bordereau
    """,nativeQuery = true)
    Optional<Depart> findDepartByBordereau(@Param("bordereau") String bordereau);

}
