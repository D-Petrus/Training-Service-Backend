package com.inqoo.trainingservice.app.controller;

import com.inqoo.trainingservice.app.models.TrainingCategory;
import com.inqoo.trainingservice.app.models.TrainingDetails;
import com.inqoo.trainingservice.app.service.TrainingCategoryService;
import com.inqoo.trainingservice.app.service.TrainingDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categries")
public class TrainingCategoryController {
    public TrainingCategoryController(TrainingCategoryService trainingCategoryService) {
        this.trainingCategoryService = trainingCategoryService;
    }

    private TrainingCategoryService trainingCategoryService;
    @GetMapping
    private List<TrainingCategory> getList() {
        return trainingCategoryService.getAllTrainingList();
    }

    @PostMapping
    private TrainingCategory saveNew(@RequestBody TrainingCategory trainingCategory) {
        return trainingCategoryService.saveNewCategory(trainingCategory);
    }

}
