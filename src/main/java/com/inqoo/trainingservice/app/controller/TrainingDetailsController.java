package com.inqoo.trainingservice.app.controller;

import com.inqoo.trainingservice.app.models.TrainingDetails;
import com.inqoo.trainingservice.app.service.TrainingDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class TrainingDetailsController {

    private TrainingDetailsService trainingDetailsService;

    public TrainingDetailsController(TrainingDetailsService trainingDetailsService) {
        this.trainingDetailsService = trainingDetailsService;
    }

    @GetMapping("/trainings")
    public List<TrainingDetails> getList() {
        return trainingDetailsService.getAllTrainingList();
    }

    @PostMapping("/trainings/add")
    public List<TrainingDetails> saveNew(@RequestBody TrainingDetails trainingDetails) {
       return trainingDetailsService.saveNewTraining(List.of(trainingDetails));
    }
}
