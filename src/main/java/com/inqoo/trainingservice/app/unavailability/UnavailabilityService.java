package com.inqoo.trainingservice.app.unavailability;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UnavailabilityService {
    private final UnavailabilityRepository unavailabilityRepository;

    public UnavailabilityService(UnavailabilityRepository unavailabilityRepository) {
        this.unavailabilityRepository = unavailabilityRepository;
    }

    Unavailability saveNewTimetable(Unavailability timetable) {
        return unavailabilityRepository.save(timetable);
    }

    public boolean checkIfNotAvailable(LocalDate dayToCheck, String firstName, String lastName) {
        Optional<Unavailability> foundedAbsence = unavailabilityRepository.checkAvailabilityForTrainer(dayToCheck, firstName, lastName);
        return foundedAbsence.isPresent();
    }

    public Unavailability saveNewUnavailability(Unavailability unavailability) {
        return unavailabilityRepository.save(unavailability);
    }
}
