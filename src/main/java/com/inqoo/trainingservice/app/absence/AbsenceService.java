package com.inqoo.trainingservice.app.absence;

import com.inqoo.trainingservice.app.order.OrderFacade;
import com.inqoo.trainingservice.app.trainer.Trainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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
        Optional<Absence> foundedAbsence = absenceRepository.checkAbsenceForTrainer(dayToCheck, firstName, lastName);
        return foundedAbsence.isPresent();
    }

    public Absence saveNewAbsence(Trainer trainer, LocalDate startAbsence, LocalDate endAbsence, AbsenceType absenceType) {
        LocalDate now = LocalDate.now();
        List<Absence> allAbsences =
                absenceRepository.countHowManyAbsencesHaveTrainerInCurrentYear(trainer.getFirstName(),
                        trainer.getLastName(),
                        LocalDate.of(now.getYear(),1,1),
                        absenceType);

        int sumOfDays = 0;
        Optional<Period> reduce = allAbsences.stream()
                .map(absence -> Period.between(absence.getStartVacation(), absence.getEndVacation()).plusDays(1))
                .reduce(Period::plus);
        if (reduce.isPresent()) {
            sumOfDays = reduce
                    .get()
                    .getDays();
        }

        if (sumOfDays > vacationDays) {
            throw new VacationLimitEndException("Vacation limit exceeded");
        }

        Absence absence = new Absence(trainer, startAbsence, endAbsence, absenceType);
        absenceRepository.save(absence);
        orderFacade.createNew(trainer.getFirstName(), trainer.getLastName(), startAbsence, endAbsence);
        return absence;

    }
}
