package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.models.TrainingCategory;
import com.inqoo.trainingservice.app.models.TrainingMain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TrainingMainServiceIT {
    @Autowired
    private TrainingMainService trainingMainService;

    @Test
    public void shouldReturnListOfCategory() {
        //given
        TrainingMain training1 = new TrainingMain(
                "JavaBasic",
                "Podstawy Javy");


        TrainingMain training2 = new TrainingMain(
                "Java Advanced",
                "Java dla zaawansowanych");
        //when
        TrainingMain savedTraining1 = trainingMainService.saveNewMain(training1);
        TrainingMain savedTraining2 = trainingMainService.saveNewMain(training2);

        //then
        assertThat(List.of(savedTraining1,savedTraining2)).isEqualTo(trainingMainService.getAllTrainingList());
    }

    @Test
    public void shouldCheckIfCatgoryIsSavedToDatabase() {
        //given
        TrainingMain training1 = new TrainingMain(
                "JavaBasic",
                "Podstawy Javy");

        //when
        TrainingMain savedTraining = trainingMainService.saveNewMain(training1);

        //then
        assertThat(savedTraining).isEqualTo(training1);
    }
    @Test
    public void shouldReturnCategoryGivenByName() {
        //given
        TrainingMain training1 = new TrainingMain(
                "JavaBasic",
                "Podstawy Javy");

        //when
        TrainingMain trainingMain = trainingMainService.saveNewMain(training1);

        //then
        assertThat(trainingMain).isEqualTo(trainingMainService.findByName("JavaBasic").get());
    }
    @Test
    public void shouldSaveIfDescriptionOfCategoryIsTooLong() {
        //given
        String txt = "";
        int numberOfChars = 300;
        for (int i = 0; i < numberOfChars; i++) {
            txt += "a";
        }
        TrainingMain training1 = new TrainingMain(
                "JavaBasic", txt);
        //when
        TrainingMain trainingMain = trainingMainService.saveNewMain(training1);
        //then
        assertThat(trainingMain).isEqualTo(training1);
    }
    @Test
    public void shouldThrowExceptionIfNameCategoryAlreadyTaken() {
        //given
        TrainingMain training1 = new TrainingMain(
                "JavaBasic",
                "Podstawy Javy");

        //then
        trainingMainService.saveNewMain(training1);
        Assertions.assertThrows(NameAlreadyTakenException.class, () -> {
            trainingMainService.saveNewMain(training1);
        });
    }

}