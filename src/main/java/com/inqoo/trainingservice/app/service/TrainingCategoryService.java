package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.exception.TooLongDescriptionException;
import com.inqoo.trainingservice.app.models.TrainingCategory;
import com.inqoo.trainingservice.app.repository.TrainingCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingCategoryService {
    private TrainingCategoryRepository trainingCategoryRepository;

    public TrainingCategoryService(TrainingCategoryRepository trainingCategoryRepository) {
        this.trainingCategoryRepository = trainingCategoryRepository;
    }

    public TrainingCategory saveNewCategory(TrainingCategory trainingCategory) {
        validateInputs(trainingCategory, trainingCategory.getNameCategory());
        return trainingCategoryRepository.save(trainingCategory);
    }

    private void validateInputs(TrainingCategory trainingCategory, String trainingName) {
        if (!validateCharacters(trainingCategory.getDescription())) {
            throw new TooLongDescriptionException("Too long description");
        }
        if (trainingCategoryRepository.findByNameCategory(trainingName).isPresent()) {
            throw new NameAlreadyTakenException("name already taken");
        }
    }

    public List<TrainingCategory> getAllTrainingList() {
        return trainingCategoryRepository.findAll();
    }

    Optional<TrainingCategory> findByName(String nameCategory) {
        return trainingCategoryRepository.findByNameCategory(nameCategory);
    }

    private boolean validateCharacters(String description) {
        int limitDescription = 301;
        return description.length() < limitDescription;
    }

}
