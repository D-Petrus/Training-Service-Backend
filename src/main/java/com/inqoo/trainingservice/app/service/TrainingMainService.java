package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.exception.TooLongDescriptionException;
import com.inqoo.trainingservice.app.models.TrainingCategory;
import com.inqoo.trainingservice.app.models.TrainingMain;
import com.inqoo.trainingservice.app.repository.TrainingCategoryRepository;
import com.inqoo.trainingservice.app.repository.TrainingMainRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingMainService {
    private TrainingMainRepository trainingMainRepository;

    public TrainingMainService(TrainingMainRepository trainingMainRepository) {
        this.trainingMainRepository = trainingMainRepository;
    }

    public TrainingMain saveNewMain(TrainingMain trainingMain) {
        validateInputs(trainingMain, trainingMain.getNameMain());
        return trainingMainRepository.save(trainingMain);
    }

    private void validateInputs(TrainingMain trainingMain, String trainingName) {
        if (!validateCharacters(trainingMain.getDescription())) {
            throw new TooLongDescriptionException("Too long description");
        }
        if (trainingMainRepository.findByNameMain(trainingName).isPresent()) {
            throw new NameAlreadyTakenException("name already taken");
        }
    }

    public List<TrainingMain> getAllTrainingList() {
        return trainingMainRepository.findAll();
    }

    Optional<TrainingMain> findByName(String nameMain) {
        return trainingMainRepository.findByNameMain(nameMain);
    }

    private boolean validateCharacters(String description) {
        int limitDescription = 301;
        return description.length() < limitDescription;
    }

}
