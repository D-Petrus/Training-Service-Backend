package com.inqoo.trainingservice.app.absence;

import com.inqoo.trainingservice.app.order.AbsenceProjection;
import com.inqoo.trainingservice.app.order.AbsenceProjectionRepository;
import com.inqoo.trainingservice.app.order.OrderFacade;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AbsenceService {
    private final AbsenceProjectionRepository absenceProjectionRepository;
    private final OrderFacade orderFacade;

    public AbsenceService(AbsenceProjectionRepository absenceProjectionRepository, OrderFacade orderFacade) {
        this.absenceProjectionRepository = absenceProjectionRepository;
        this.orderFacade = orderFacade;
    }

    public boolean checkIfNotAvailable(LocalDate dayToCheck, String firstName, String lastName) {
        Optional<AbsenceProjection> foundedAbsence = absenceProjectionRepository.checkAvailabilityForTrainer(dayToCheck, firstName, lastName);
        return foundedAbsence.isPresent();
    }

    public AbsenceProjection saveNewAbsence(AbsenceProjection absenceProjection) {
        AbsenceProjection result = absenceProjectionRepository.save(absenceProjection);
        orderFacade.createNew(result.getTrainer().getFirstName(),
                result.getTrainer().getLastName(),
                result.getStartAbsence(),
                result.getEndDate());
        return result;
    }
}
