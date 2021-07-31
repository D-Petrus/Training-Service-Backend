package com.inqoo.trainingservice.app.converter;

import com.inqoo.trainingservice.app.DTO.CategoryDTO;
import com.inqoo.trainingservice.app.DTO.TrainerDTO;
import com.inqoo.trainingservice.app.models.Category;
import com.inqoo.trainingservice.app.models.Trainer;
import org.springframework.stereotype.Component;

@Component
public class TrainerConverter {
    public TrainerDTO entityToDTO (Trainer trainer){
        TrainerDTO trainerDTO = new TrainerDTO(
        trainer.getFirstName(),
        trainer.getLastName(),
        trainer.getExperience()
        );
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
