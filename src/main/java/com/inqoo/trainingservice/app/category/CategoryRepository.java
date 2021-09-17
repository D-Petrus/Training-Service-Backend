package com.inqoo.trainingservice.app.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    List<Category> findCategoryByName(String name);

    @Query("select name from Category")
    List<String> getAllCategoryName();
}
