package com.inqoo.trainingservice.app.absence;

import com.inqoo.trainingservice.app.trainer.Trainer;
import com.inqoo.trainingservice.app.trainer.TrainerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AbsenceServiceTest {
    @Autowired
    private AbsenceService absenceService;
    @Autowired
    private TrainerService trainerService;

    @Test
    public void shouldCheckAvailableForTrainerByGivenDate() {
        //given
        Trainer trainer = new Trainer("Marcin", "Butora", "none", 505009546L, "mbutora@gmail.com");
        Trainer savedTrainerToDB = trainerService.saveNewTrainer(trainer);
        Absence absence = new Absence(savedTrainerToDB, LocalDate.of(2021,10,10), LocalDate.of(2021,10,15), AbsenceType.NIEOBECNOŚĆ);
        Absence saveNewAbsence = absenceService.saveNewAbsence(absence);

        //when
        boolean ifNotAvailable = absenceService.checkIfNotAvailable(saveNewAbsence.getStartVacation(), "Marcin", "Butora");

        //then
        assertThat(ifNotAvailable).isTrue();
    }
    @Test
    public void shouldCheckTakeVacationTrainerByGivenDate() {
        Trainer trainer = new Trainer("Marcin", "Butora", "none", 505009546L, "mbutora@gmail.com");
        Trainer savedTrainerToDB = trainerService.saveNewTrainer(trainer);
        Absence absence = new Absence(savedTrainerToDB, LocalDate.of(2021,10,2), LocalDate.of(2021,10,28), AbsenceType.URLOP);
        Absence saveNewAbsence = absenceService.saveNewAbsence(absence);

        //when
        Absence absence2 = new Absence(savedTrainerToDB, LocalDate.of(2021,11,2), LocalDate.of(2021,11,28), AbsenceType.URLOP);

        //then
        Assertions.assertThrows(VacationLimitEndException.class, () -> absenceService.saveNewAbsence(absence2));
    }
}