package com.inqoo.trainingservice.app.absence;

import com.inqoo.trainingservice.app.order.OrderFacade;
import com.inqoo.trainingservice.app.trainer.Trainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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
        LocalDate now = LocalDate.now();
        List<Absence> allAbsences = absenceRepository.countHowManyAbsencesHaveTrainerInCurrentYear(trainer.getFirstName(), trainer.getLastName(), LocalDate.now());

        Absence absence = new Absence(trainer, startAbsence, endAbsence, absenceType);
        absenceRepository.save(absence);
        orderFacade.createNew(trainer.getFirstName(), trainer.getLastName(), startAbsence, endAbsence);
        return absence;

    }
}
