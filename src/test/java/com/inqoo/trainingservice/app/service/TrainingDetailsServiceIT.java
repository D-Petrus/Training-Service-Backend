package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.models.TrainingDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
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
        List<TrainingDetails> savedTrainings = trainingDetailsService.saveNewTraining(Arrays.asList(training1, training2));

        //then
        assertThat(savedTrainings).isEqualTo(trainingDetailsService.getAllTrainingList());
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
        List<TrainingDetails> savedTraining = trainingDetailsService.saveNewTraining(List.of(training1));

        //then
        assertThat(savedTraining).isNotEmpty();
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
        List<TrainingDetails> savedTraining = trainingDetailsService.saveNewTraining(List.of(training1));

        //then
        assertThat(savedTraining).isEqualTo(trainingDetailsService.findByName("Spring Boot w Javie"));
    }

}