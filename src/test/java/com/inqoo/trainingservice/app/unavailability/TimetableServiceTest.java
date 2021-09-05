package com.inqoo.trainingservice.app.unavailability;

import com.inqoo.trainingservice.app.trainer.Trainer;
import com.inqoo.trainingservice.app.trainer.TrainerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TimetableServiceTest {
    @Autowired
    private UnavailabilityService timetableService;
    @Autowired
    private TrainerService trainerService;

    @Test
    public void shouldCheckAvailableForTrainerByGivenDate() {
        //given
        Trainer trainer = new Trainer("Marcin", "Butora", "none", 505009546L, "mbutora@gmail.com");
        Trainer savedTrainerToDB = trainerService.saveNewTrainer(trainer);
        Unavailability timetable = new Unavailability(savedTrainerToDB, LocalDate.of(2021,10,10));
        Unavailability savedTimetable = timetableService.saveNewTimetable(timetable);

        //when
        boolean ifNotAvailable = timetableService.checkIfNotAvailable(savedTimetable.getDayOfAbsence(), "Marcin", "Butora");

        //then
        assertThat(ifNotAvailable).isTrue();
    }

}