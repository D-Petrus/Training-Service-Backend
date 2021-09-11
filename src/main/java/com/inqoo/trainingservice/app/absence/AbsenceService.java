package com.inqoo.trainingservice.app.absence;

import com.inqoo.trainingservice.app.order.OrderFacade;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AbsenceService {
    private final AbsenceRepository absenceRepository;
    private final OrderFacade orderFacade;

    public AbsenceService(AbsenceRepository absenceRepository, OrderFacade orderFacade) {
        this.absenceRepository = absenceRepository;
        this.orderFacade = orderFacade;
    }

    public boolean checkIfNotAvailable(LocalDate dayToCheck, String firstName, String lastName) {
        Optional<Absence> foundedAbsence = absenceRepository.checkAvailabilityForTrainer(dayToCheck, firstName, lastName);
        return foundedAbsence.isPresent();
    }

    public Absence saveNewAbsence(Absence absenceProjection) {
        Absence result = absenceRepository.save(absenceProjection);
        orderFacade.createNew(result.getTrainer().getFirstName(),
                result.getTrainer().getLastName(),
                result.getStartVacation(),
                result.getEndVacation());
        return result;
    }
}
