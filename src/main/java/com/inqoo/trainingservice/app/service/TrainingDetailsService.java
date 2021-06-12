package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.models.TrainingDetails;
import com.inqoo.trainingservice.app.repository.TrainingDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingDetailsService {

    private TrainingDetailsRepository trainingDetailsRepository;

    public TrainingDetailsService(TrainingDetailsRepository trainingDetailsRepository) {
        this.trainingDetailsRepository = trainingDetailsRepository;
    }

    public TrainingDetails saveNewTraining(TrainingDetails trainingDetails) {
        return trainingDetailsRepository.save(trainingDetails);
    }

    public List<TrainingDetails> getAllTrainingList() {
        return trainingDetailsRepository.findAll();
    }

    public TrainingDetails findByName(String nameDetails) {
        return trainingDetailsRepository.findByNameDetails(nameDetails);
    }
}
