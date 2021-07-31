package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.exception.TooLongDescriptionException;
import com.inqoo.trainingservice.app.models.Trainer;
import com.inqoo.trainingservice.app.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class TrainerService {
    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Trainer saveNewTrainer(Trainer trainer) {
        validateInputs(trainer);
        return trainerRepository.save(trainer);

    }
    Optional<Trainer> findByFirstAndLastName(String firstName, String lastName) {
        return trainerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Trainer> getAllTrainerList() {
        return trainerRepository.findAll();
    }

    private void validateInputs( Trainer trainer) {
        if (trainerRepository.findByFirstNameAndLastName(trainer.getFirstName(), trainer.getLastName()).isPresent()) {
            throw new NameAlreadyTakenException("This trainer is already saved");
        }

        if (trainer.getExperience().length() > 4000) {
            throw new TooLongDescriptionException("This description of experience is too long");
        }
    }
}

