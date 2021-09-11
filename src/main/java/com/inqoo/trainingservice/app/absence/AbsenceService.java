package com.inqoo.trainingservice.app.absence;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AbsenceService {
    private final AbsenceRepository absenceRepository;

    public AbsenceService(AbsenceRepository unavailabilityRepository) {
        this.absenceRepository = unavailabilityRepository;
    }

    public boolean checkIfNotAvailable(LocalDate dayToCheck, String firstName, String lastName) {
        Optional<Absence> foundedAbsence = absenceRepository.checkAvailabilityForTrainer(dayToCheck, firstName, lastName);
        return foundedAbsence.isPresent();
    }

    public Absence saveNewAbsence(Absence absence) {
        return absenceRepository.save(absence);
    }
}
