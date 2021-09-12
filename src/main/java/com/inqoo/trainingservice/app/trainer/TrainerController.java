package com.inqoo.trainingservice.app.trainer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/trainer")
class TrainerController {
    private final TrainerService trainerService;

    private TrainerController(TrainerService trainerService) {this.trainerService = trainerService;}

    @GetMapping
    List<Trainer> geAllTrainer() {
        return trainerService.getAllTrainerList();
    }

    @PostMapping
    Trainer saveNew(@RequestBody Trainer trainer) { return trainerService.saveNewTrainer(trainer);}

}
