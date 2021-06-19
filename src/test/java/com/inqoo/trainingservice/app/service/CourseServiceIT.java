package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.exception.TooLongDescriptionException;
import com.inqoo.trainingservice.app.models.Course;
import org.apache.commons.lang3.RandomStringUtils;
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
class CourseServiceIT {
    @Autowired
    private CourseService trainingDetailsService;

    @Test
    public void shouldReturnListOfTrainings() {
        //given
        Course course1 = new Course(
                "Spring Boot w Javie",
                "Kurs na temat Spring Boot w Javie",
                2L,
                BigDecimal.valueOf(2000));

        Course course2 = new Course(
                "Hibernate w Javie",
                "Kurs na temat Hibernate w Javie",
                2L,
                BigDecimal.valueOf(3000));
        //when
        Course savedCourse1 = trainingDetailsService.saveNewCourse(course1);
        Course savedCourse2 = trainingDetailsService.saveNewCourse(course2);

        //then
        assertThat(List.of(savedCourse1,savedCourse2)).isEqualTo(trainingDetailsService.getAllCoursesList());
    }

    @Test
    public void shouldCheckIfTrainingIsSavedToDatabase() {
        //given
        Course course = new Course(
                "Spring Boot w Javie",
                "Kurs na temat Spring Boot w Javie",
                2L,
                BigDecimal.valueOf(2000));

        //when
        Course savedCourse = trainingDetailsService.saveNewCourse(course);

        //then
        assertThat(savedCourse).isEqualTo(course);
    }

    @Test
    public void shouldReturnTrainingGivenByName() {
        //given
        Course course = new Course(
                "Spring Boot w Javie",
                "Kurs na temat Spring Boot w Javie",
                2L,
                BigDecimal.valueOf(2000));

        //when
        Course savedCourse = trainingDetailsService.saveNewCourse(course);

        //then
        assertThat(savedCourse).isEqualTo(trainingDetailsService.findByName("Spring Boot w Javie").get());
    }
    @Test
    public void shouldSaveIfDescriptionTooLong() {
        //given
        String txt = "";
        int numberOfChars = 200;
        for (int i = 0; i < numberOfChars; i++) {
            txt += "a";
        }
        Course course = new Course(
                "Spring Boot w Javie",
                txt,
                2L,
                BigDecimal.valueOf(2000));
        //when
        Course savedCourse = trainingDetailsService.saveNewCourse(course);
        //then
        assertThat(savedCourse).isEqualTo(course);
    }
    @Test
    public void shouldNotSaveIfDescriptionTooLong() {
        //given
        String generatedTxt = RandomStringUtils.randomAlphanumeric(201);
        Course course = new Course(
                "Spring Boot w Javie",
                generatedTxt,
                2L,
                BigDecimal.valueOf(2000));

        //then
        Assertions.assertThrows(TooLongDescriptionException.class, () -> {
            trainingDetailsService.saveNewCourse(course);
        });
    }

    @Test
    public void shouldThrowExceptionIfNameAlreadyTaken() {
        //given
        Course course = new Course(
                "Spring Boot w Javie",
                "Kurs na temat Spring Boot w Javie",
                2L,
                BigDecimal.valueOf(2000));

        //then
        trainingDetailsService.saveNewCourse(course);
        Assertions.assertThrows(NameAlreadyTakenException.class, () -> {
           trainingDetailsService.saveNewCourse(course);
        });
    }

}