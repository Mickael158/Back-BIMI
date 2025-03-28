package com.example.bimix.repository;

import com.example.bimix.model.CatOR;
import com.example.bimix.model.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatORRepository extends JpaRepository<CatOR, Integer> {
    @Query(value = """
    select * from CatOr where nom=:nom and codeGrade=:codeGrade and indice=:indice
    """,nativeQuery = true)
    Optional<CatOR> findCatOrByNomAndCodeGradeAndIndice(@Param("nom") String nom, @Param("codeGrade") String codeGrade, @Param("indice") String indice);
}
