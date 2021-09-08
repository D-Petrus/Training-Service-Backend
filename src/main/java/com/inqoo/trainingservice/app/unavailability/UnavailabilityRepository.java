package com.inqoo.trainingservice.app.unavailability;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface UnavailabilityRepository extends JpaRepository<Unavailability, Long> {
    @Query("select t from Unavailability t where t.trainer.firstName = :firstName and t.trainer.lastName = :lastName and dayOfAbsence = :dayToCheck")
    Optional<Unavailability> checkAvailabilityForTrainer(LocalDate dayToCheck, String firstName, String lastName);

}
