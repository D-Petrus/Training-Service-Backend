package com.inqoo.trainingservice.app.repository;

import com.inqoo.trainingservice.app.models.TrainingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingDetailsRepository extends JpaRepository<TrainingDetails, Long> {
    TrainingDetails findByNameDetails(String nameDetails);
}
