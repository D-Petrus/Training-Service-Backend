package com.inqoo.trainingservice.app.order;

import com.inqoo.trainingservice.app.absence.AbsenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AbsenceProjectionRepository extends JpaRepository<AbsenceProjection, Long> {
    @Query("select a from AbsenceProjection a where a.trainer.firstName = :firstName and a.trainer.lastName = :lastName and :dayToCheck between a.startAbsence and a.endDate")
    Optional<AbsenceProjection> checkAvailabilityForTrainer(LocalDate dayToCheck, String firstName, String lastName);

}
