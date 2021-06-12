package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.models.TrainingCategory;
import com.inqoo.trainingservice.app.models.TrainingDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
class TrainingCategoryServiceIT {
    @Autowired
    private TrainingCategoryService trainingCategoryService;

    @Test
    public void shouldReturnListOfCategory() {
        //given
        TrainingCategory training1 = new TrainingCategory(
                "JavaBasic",
                "Podstawy Javy");


        TrainingCategory training2 = new TrainingCategory(
                "Java Advanced",
                "Java dla zaawansowanych");
        //when
        TrainingCategory savedTraining1 = trainingCategoryService.saveNewCategory(training1);
        TrainingCategory savedTraining2 = trainingCategoryService.saveNewCategory(training2);

        //then
        assertThat(List.of(savedTraining1,savedTraining2)).isEqualTo(trainingCategoryService.getAllTrainingList());
    }

    @Test
    public void shouldCheckIfCatgoryIsSavedToDatabase() {
        //given
        TrainingCategory training1 = new TrainingCategory(
                "JavaBasic",
                "Podstawy Javy");

        //when
        TrainingCategory savedTraining = trainingCategoryService.saveNewCategory(training1);

        //then
        assertThat(savedTraining).isEqualTo(training1);
    }
    @Test
    public void shouldReturnCategoryGivenByName() {
        //given
        TrainingCategory training1 = new TrainingCategory(
                "JavaBasic",
                "Podstawy Javy");

        //when
        TrainingCategory trainingCategory = trainingCategoryService.saveNewCategory(training1);

        //then
        assertThat(trainingCategory).isEqualTo(trainingCategoryService.findByName("JavaBasic").get());
    }
    @Test
    public void shouldSaveIfDescriptionOfCategoryIsTooLong() {
        //given
        String txt = "";
        int numberOfChars = 300;
        for (int i = 0; i < numberOfChars; i++) {
            txt += "a";
        }
        TrainingCategory training1 = new TrainingCategory(
                "JavaBasic", txt);
        //when
        TrainingCategory trainingCategory = trainingCategoryService.saveNewCategory(training1);
        //then
        assertThat(trainingCategory).isEqualTo(training1);
    }
    @Test
    public void shouldThrowExceptionIfNameCategoryAlreadyTaken() {
        //given
        TrainingCategory training1 = new TrainingCategory(
                "JavaBasic",
                "Podstawy Javy");

        //then
        trainingCategoryService.saveNewCategory(training1);
        Assertions.assertThrows(NameAlreadyTakenException.class, () -> {
            trainingCategoryService.saveNewCategory(training1);
        });
    }

}