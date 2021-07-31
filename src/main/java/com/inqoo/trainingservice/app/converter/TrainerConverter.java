package com.inqoo.trainingservice.app.converter;

import com.inqoo.trainingservice.app.DTO.TrainerDTO;
import com.inqoo.trainingservice.app.models.Trainer;
import org.springframework.stereotype.Component;

@Component
public class TrainerConverter {
    public TrainerDTO entityToDTO (Trainer trainer){
        TrainerDTO trainerDTO = new TrainerDTO();
        trainerDTO.setFirstName(trainer.getFirstName());
        trainerDTO.setLastName(trainer.getLastName());
        trainerDTO.setExperience(trainer.getExperience());
        return trainerDTO;
    }

    public Trainer dtoToEntity (TrainerDTO trainerDTO){
        Trainer trainer = new Trainer();
        trainer.setFirstName(trainerDTO.getFirstName());
        trainer.setLastName(trainerDTO.getLastName());
        trainer.setExperience(trainerDTO.getExperience());
        return trainer;
    }
}
