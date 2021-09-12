package com.inqoo.trainingservice.app.trainer;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/trainer")
class TrainerController {
    private TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {this.trainerService = trainerService;}

    @GetMapping
    private List<Trainer> geAllTrainer(){return trainerService.getAllTrainerList();}
    @PostMapping
    private Trainer saveNew(@RequestBody Trainer trainer) { return trainerService.saveNewTrainer(trainer);}

}
