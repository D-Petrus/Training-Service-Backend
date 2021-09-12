package com.inqoo.trainingservice.app.trainer;

import com.inqoo.trainingservice.app.exception.TooLongDescriptionException;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
            throw new TrainerIsAlreadySavedException("Trainer is already exist!");
        }

        if (trainer.getExperience().length() > 4000) {
            throw new TooLongDescriptionException("Too long description of experience!");
        }
    }
}

