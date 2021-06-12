package com.inqoo.trainingservice.app.repository;

import com.inqoo.trainingservice.app.models.TrainingCategory;
import com.inqoo.trainingservice.app.models.TrainingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingCategoryRepository extends JpaRepository<TrainingCategory, Long> {
    Optional<TrainingCategory> findByNameCategory(String nameCategory);
}
