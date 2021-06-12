package com.inqoo.trainingservice.app.repository;

import com.inqoo.trainingservice.app.models.TrainingCategory;
import com.inqoo.trainingservice.app.models.TrainingMain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainingMainRepository extends JpaRepository<TrainingMain, Long> {
    Optional<TrainingMain> findByNameMain(String nameMain);
}
