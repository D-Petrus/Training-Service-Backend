package com.inqoo.trainingservice.app.trainer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public Trainer saveNewTrainer(Trainer trainer) {
        validateInputs(trainer);
        return trainerRepository.save(trainer);

    }

    public Optional<Trainer> findByFirstAndLastName(String firstName, String lastName) {
        return trainerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Trainer> getAllTrainerList() {
        return trainerRepository.findAll();
    }

    private void validateInputs(Trainer trainer) {
        if (trainerRepository.findByFirstNameAndLastName(trainer.getFirstName(), trainer.getLastName()).isPresent()) {
            throw new TrainerIsAlreadySavedException();
        }

        if (trainer.getExperience().length() > 4000) {
            throw new TooLongDescriptionOfExperienceException();
        }
    }
}

