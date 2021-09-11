package com.inqoo.trainingservice.app.absence;

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
    private AbsenceService timetableService;
    @Autowired
    private TrainerService trainerService;

    @Test
    public void shouldCheckAvailableForTrainerByGivenDate() {
        //given
        Trainer trainer = new Trainer("Marcin", "Butora", "none", 505009546L, "mbutora@gmail.com");
        Trainer savedTrainerToDB = trainerService.saveNewTrainer(trainer);
        Absence timetable = new Absence(savedTrainerToDB, LocalDate.of(2021,10,10), LocalDate.of(2021,10,15), AbsenceType.NIEOBECNOŚĆ);
        Absence savedTimetable = timetableService.saveNewTimetable(timetable);

        //when
        boolean ifNotAvailable = timetableService.checkIfNotAvailable(savedTimetable.getStartVacation(), "Marcin", "Butora");

        //then
        assertThat(ifNotAvailable).isTrue();
    }

}