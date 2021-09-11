package com.inqoo.trainingservice.app.absence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    @Query("select a from Absence a where a.trainer.firstName = :firstName and a.trainer.lastName = :lastName and :dayToCheck between a.startVacation and a.endVacation")
    Optional<Absence> checkAvailabilityForTrainer(LocalDate dayToCheck, String firstName, String lastName);

    @Query("select a from Absence a where a.trainer.firstName = :firstName and a.trainer.lastName = :lastName and a.startVacation >= :currentYeat")
    List<Absence> countHowManyAbsencesHaveTrainerInCurrentYear(String firstName, String lastName, LocalDate currentYear);

}
