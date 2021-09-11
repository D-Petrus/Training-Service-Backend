package com.inqoo.trainingservice.app.absence;

import com.inqoo.trainingservice.app.order.OrderFacade;
import com.inqoo.trainingservice.app.trainer.Trainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AbsenceService {
    private final AbsenceRepository absenceRepository;
    private final OrderFacade orderFacade;
    private final int vacationDays;

    public AbsenceService(AbsenceRepository absenceRepository,
                          OrderFacade orderFacade,
                          @Value("${max.vacation.days}") int vacationDays) {
        this.absenceRepository = absenceRepository;
        this.orderFacade = orderFacade;
        this.vacationDays = vacationDays;
    }

    public boolean checkIfNotAvailable(LocalDate dayToCheck, String firstName, String lastName) {
        Optional<Absence> foundedAbsence = absenceRepository.checkAvailabilityForTrainer(dayToCheck, firstName, lastName);
        return foundedAbsence.isPresent();
    }

    public Absence saveNewAbsence(Trainer trainer, LocalDate startAbsence, LocalDate endAbsence, AbsenceType absenceType) {
        if (absenceRepository.countHowManyAbsenceDaysTheTrainerHasSignedUp(
                trainer.getFirstName(), trainer.getLastName(), startAbsence) > vacationDays) {
            throw new RuntimeException("Vacation days limit exceeded");
        }
        Absence absence = new Absence(trainer, startAbsence, endAbsence, absenceType);
        absenceRepository.save(absence);
        orderFacade.createNew(trainer.getFirstName(), trainer.getLastName(), startAbsence, endAbsence);
        return absence;

    }

    public int howManyDays(String firstName, String lastName, LocalDate currentYear, AbsenceType absenceType) {
        return absenceRepository.countHowManyAbsenceDaysTheTrainerHasSignedUp(firstName, lastName, currentYear);
    }
}
