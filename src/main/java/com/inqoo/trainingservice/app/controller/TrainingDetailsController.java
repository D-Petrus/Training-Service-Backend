package com.inqoo.trainingservice.app.controller;

import com.inqoo.trainingservice.app.models.TrainingDetails;
import com.inqoo.trainingservice.app.service.TrainingDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainings")
public class TrainingDetailsController {

    private TrainingDetailsService trainingDetailsService;

    private TrainingDetailsController(TrainingDetailsService trainingDetailsService) {
        this.trainingDetailsService = trainingDetailsService;
    }

    @GetMapping
    private List<TrainingDetails> getList() {
        return trainingDetailsService.getAllTrainingList();
    }

    @PostMapping
    private TrainingDetails saveNew(@RequestBody TrainingDetails trainingDetails) {
       return trainingDetailsService.saveNewTraining(trainingDetails);
    }
}
