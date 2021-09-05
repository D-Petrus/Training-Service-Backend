package com.inqoo.trainingservice.app.timetable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    @Query("select t from Timetable t where t.trainer.firstName = :firstName and t.trainer.lastName = :lastName and dayOfAbsence = :dayToCheck")
    Optional<Timetable> checkAvailabilityForTrainer(LocalDate dayToCheck, String firstName, String lastName);

}
