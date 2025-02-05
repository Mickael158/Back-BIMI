package com.example.bimix.repository;

import com.example.bimix.model.CatOR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatORRepository extends JpaRepository<CatOR, Integer> {
}
