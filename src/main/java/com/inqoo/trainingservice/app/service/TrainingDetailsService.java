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

    public List<TrainingDetails> saveNewTraining(Iterable<TrainingDetails> trainingDetails) {
        return trainingDetailsRepository.saveAll(trainingDetails);
    }

    public List<TrainingDetails> getAllTrainingList() {
        return trainingDetailsRepository.findAll();
    }

    public List<TrainingDetails> findByName(String nameDetails) {
        return trainingDetailsRepository.findByNameDetails(nameDetails);
    }
}
