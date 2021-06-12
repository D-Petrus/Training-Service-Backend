package com.inqoo.trainingservice.app.controller;

import com.inqoo.trainingservice.app.models.TrainingCategory;
import com.inqoo.trainingservice.app.models.TrainingDetails;
import com.inqoo.trainingservice.app.models.TrainingMain;
import com.inqoo.trainingservice.app.service.TrainingCategoryService;
import com.inqoo.trainingservice.app.service.TrainingDetailsService;
import com.inqoo.trainingservice.app.service.TrainingMainService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main")
public class TrainingMainController {
    public TrainingMainController(TrainingMainService trainingMainService) {
        this.trainingMainService = trainingMainService;
    }

    private TrainingMainService trainingMainService;
    @GetMapping
    private List<TrainingMain> getList() {
        return trainingMainService.getAllTrainingList();
    }

    @PostMapping
    private TrainingMain saveNew(@RequestBody TrainingMain trainingMain) {
        return trainingMainService.saveNewMain(trainingMain);
    }

}
