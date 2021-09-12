package com.inqoo.trainingservice.app.trainer;

import com.inqoo.trainingservice.app.exception.TooLongDescriptionException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TrainerServiceIT {
    @Autowired
    private TrainerService trainerService;
    @Autowired
    private TrainerConverter trainerConverter;

    @Test
    public void shouldReturnListOfTrainer(){
        //given
        TrainerDTO trainer1 = new TrainerDTO(
                "Jan",
                "Kowalski",
                "2 years experience in Java");
        TrainerDTO trainer2 = new TrainerDTO(
                "Paweł",
                "Nowak",
                "3 years experience in Angular");
        //when
        Trainer savedTrainer1DTO = trainerService.saveNewTrainer(trainerConverter.dtoToEntity(trainer1));
        Trainer savedTrainer2DTO = trainerService.saveNewTrainer(trainerConverter.dtoToEntity(trainer2));
        //then
        assertThat(List.of(savedTrainer1DTO, savedTrainer2DTO)).isEqualTo(trainerService.getAllTrainerList());
    }

    @Test
    public void shouldReturnTrainerGivenByFirstAndLastName() {
        //given
        TrainerDTO trainer1 = new TrainerDTO(
                "Jan",
                "Kowalski",
                "3 years in Java");
        //when
       Trainer savedTrainer1DTO = trainerService.saveNewTrainer(trainerConverter.dtoToEntity(trainer1));
        //then
        assertThat(savedTrainer1DTO).isEqualTo(trainerService.findByFirstAndLastName("Jan", "Kowalski").get());
    }

    @Test
    public void shouldNotSaveIfDescriptionOfExperienceIsToLong(){
        //given

        String generatedTxt = RandomStringUtils.randomAlphanumeric(4001);
        TrainerDTO trainer1 = new TrainerDTO(
                "Jan",
                "Kowalski",
                generatedTxt);

        //then
        Assertions.assertThrows(TooLongDescriptionException.class, () -> {
            trainerService.saveNewTrainer(trainerConverter.dtoToEntity(trainer1));
        });
    }

    @Test
    public void shouldNotSaveTrainerIfIsAlreadySaved(){
        //given

        String generatedTxt = RandomStringUtils.randomAlphanumeric(4000);
        TrainerDTO trainer1 = new TrainerDTO(
                "Jan",
                "Kowalski",
                generatedTxt);
        trainerService.saveNewTrainer(trainerConverter.dtoToEntity(trainer1));

        //given
        String generatedTxt2 = RandomStringUtils.randomAlphanumeric(4000);
        TrainerDTO trainer2 = new TrainerDTO(
                "Jan",
                "Kowalski",
                generatedTxt2);

        //then
        Assertions.assertThrows(TrainerIsAlreadySavedException.class, () -> {
            trainerService.saveNewTrainer(trainerConverter.dtoToEntity(trainer2));
        });
    }
}
