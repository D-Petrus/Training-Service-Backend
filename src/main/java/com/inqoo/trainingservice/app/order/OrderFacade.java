package com.inqoo.trainingservice.app.order;

import com.inqoo.trainingservice.app.trainer.Trainer;
import com.inqoo.trainingservice.app.trainer.TrainerRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class OrderFacade {
    private final AbsenceProjectionRepository absenceProjectionRepository;
    private final TrainerRepository trainerRepository;

    public OrderFacade(AbsenceProjectionRepository absenceProjectionRepository, TrainerRepository trainerRepository) {
        this.absenceProjectionRepository = absenceProjectionRepository;
        this.trainerRepository = trainerRepository;
    }

    public AbsenceProjection createNew(String firstName, String lastName, LocalDate startAbsence, LocalDate endAbsence) {
        Optional<Trainer> foundedTrainer = trainerRepository.findByFirstNameAndLastName(firstName, lastName);
        if (foundedTrainer.isEmpty()) {
            throw new RuntimeException("Absence not created");
        }
        AbsenceProjection absenceProjection = new AbsenceProjection(foundedTrainer.get(), startAbsence, endAbsence);
        return absenceProjectionRepository.save(absenceProjection);
    }


}
