package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.models.TrainingDetails;
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
class TrainingDetailsServiceIT {
    @Autowired
    private TrainingDetailsService trainingDetailsService;

    @Test
    public void shouldReturnListOfTrainings() {
        //given
        TrainingDetails training1 = new TrainingDetails(
                "Spring Boot w Javie",
                "Kurs na temat Spring Boot w Javie",
                Instant.now(),
                BigDecimal.valueOf(2000));

        TrainingDetails training2 = new TrainingDetails(
                "Hibernate w Javie",
                "Kurs na temat Hibernate w Javie",
                Instant.now(),
                BigDecimal.valueOf(3000));
        //when
        TrainingDetails savedTraining1 = trainingDetailsService.saveNewTraining(training1);
        TrainingDetails savedTraining2 = trainingDetailsService.saveNewTraining(training2);

        //then
        assertThat(List.of(savedTraining1,savedTraining2)).isEqualTo(trainingDetailsService.getAllTrainingList());
    }

    @Test
    public void shouldCheckIfTrainingIsSavedToDatabase() {
        //given
        TrainingDetails training1 = new TrainingDetails(
                "Spring Boot w Javie",
                "Kurs na temat Spring Boot w Javie",
                Instant.now(),
                BigDecimal.valueOf(2000));

        //when
        TrainingDetails savedTraining = trainingDetailsService.saveNewTraining(training1);

        //then
        assertThat(savedTraining).isEqualTo(training1);
    }

    @Test
    public void shouldReturnTrainingGivenByName() {
        //given
        TrainingDetails training1 = new TrainingDetails(
                "Spring Boot w Javie",
                "Kurs na temat Spring Boot w Javie",
                Instant.now(),
                BigDecimal.valueOf(2000));

        //when
        TrainingDetails trainingDetails = trainingDetailsService.saveNewTraining(training1);

        //then
        assertThat(trainingDetails).isEqualTo(trainingDetailsService.findByName("Spring Boot w Javie"));
    }

}