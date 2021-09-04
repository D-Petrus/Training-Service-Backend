package com.inqoo.trainingservice.app.subcategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    Optional<Subcategory> findByName(String name);

    @Query("select name from Subcategory")
    List<String> getAllSubcategoryName();
}
