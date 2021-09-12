package com.inqoo.trainingservice.app.trainer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/trainer")
@Slf4j
class TrainerController {
    private final TrainerService trainerService;

    private TrainerController(TrainerService trainerService) {this.trainerService = trainerService;}

    @GetMapping
    List<Trainer> geAllTrainer() {
        log.info("Getting list of trainers");
        return trainerService.getAllTrainerList();
    }

    @PostMapping
    Trainer saveNew(@RequestBody Trainer trainer) {
        log.info("Saving new trainer: "+trainer.getFirstName()+" "+trainer.getLastName());
        return trainerService.saveNewTrainer(trainer);
    }

}
