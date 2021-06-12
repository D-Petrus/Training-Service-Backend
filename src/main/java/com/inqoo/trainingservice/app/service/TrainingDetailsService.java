package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.exception.TooLongDescriptionException;
import com.inqoo.trainingservice.app.models.TrainingDetails;
import com.inqoo.trainingservice.app.repository.TrainingDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingDetailsService {

    private TrainingDetailsRepository trainingDetailsRepository;

    public TrainingDetailsService(TrainingDetailsRepository trainingDetailsRepository) {
        this.trainingDetailsRepository = trainingDetailsRepository;
    }

    public TrainingDetails saveNewTraining(TrainingDetails trainingDetails) {
        validateInputs(trainingDetails, trainingDetails.getNameDetails());
        return trainingDetailsRepository.save(trainingDetails);
    }

    private void validateInputs(TrainingDetails trainingDetails, String trainingName) {
        if (!validateCharacters(trainingDetails.getDescription())) {
            throw new TooLongDescriptionException("Too long description");
        }
        if (trainingDetailsRepository.findByNameDetails(trainingName).isPresent()) {
            throw new NameAlreadyTakenException("name already taken");
        }

    }

    public List<TrainingDetails> getAllTrainingList() {
        return trainingDetailsRepository.findAll();
    }

    Optional<TrainingDetails> findByName(String nameDetails) {
        return trainingDetailsRepository.findByNameDetails(nameDetails);
    }

    private boolean validateCharacters(String description) {
        int limitDescription = 201;
        return description.length() < limitDescription;
    }
}
