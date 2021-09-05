package com.inqoo.trainingservice.app.timetable;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TimetableService {
    private final TimetableRepository timetableRepository;

    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    Timetable saveNewTimetable(Timetable timetable) {
        return timetableRepository.save(timetable);
    }

    boolean checkIfNotAvailable(LocalDate dayToCheck, String firstName, String lastName) {
        Optional<Timetable> foundedAbsence = timetableRepository.checkAvailabilityForTrainer(dayToCheck, firstName, lastName);
        return foundedAbsence.isPresent();
    }
}
