package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.exception.TooLongDescriptionException;
import com.inqoo.trainingservice.app.models.Category;
import com.inqoo.trainingservice.app.models.Course;
import com.inqoo.trainingservice.app.models.Subcategory;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CourseServiceIT {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubcategoryService subcategoryService;

    @Test
    public void shouldReturnListOfCourses() {
        //given
        Category category = new Category("Java", "Kurs Java");
        categoryService.saveNewCategory(category);
        Subcategory subcategory = new Subcategory("Spring", "Kurs Spring", category);
        subcategoryService.saveNewSubcategory(subcategory);        Course course1 = new Course(
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
        Course savedCourse1 = courseService.saveNewCourse(subcategory.getName(), course1);
        Course savedCourse2 = courseService.saveNewCourse(subcategory.getName(), course2);

        //then
        assertThat(List.of(savedCourse1,savedCourse2)).isEqualTo(courseService.getAllCoursesList());
    }

    @Test
    public void shouldCheckIfCourseIsSavedToDatabase() {
        //given
        Category category = new Category("Java", "Kurs Java");
        categoryService.saveNewCategory(category);
        Subcategory subcategory = new Subcategory("Spring", "Kurs Spring", category);
        subcategoryService.saveNewSubcategory(subcategory);
        Course course = new Course(
                "Spring Boot w Javie",
                "Kurs na temat Spring Boot w Javie",
                2L,
                BigDecimal.valueOf(2000));

        //when
        Course savedCourse = courseService.saveNewCourse(subcategory.getName(), course);

        //then
        assertThat(savedCourse.getName()).isEqualTo(course.getName());
    }

    @Test
    public void shouldReturnCourseGivenByName() {
        //given
        Category category = new Category("Java", "Kurs Java");
        categoryService.saveNewCategory(category);
        Subcategory subcategory = new Subcategory("Spring", "Kurs Spring", category);
        subcategoryService.saveNewSubcategory(subcategory);
        Course course = new Course(
                "Spring Boot w Javie",
                "Kurs na temat Spring Boot w Javie",
                2L,
                BigDecimal.valueOf(2000));

        //when
        Course savedCourse = courseService.saveNewCourse(subcategory.getName(), course);

        //then
        assertThat(savedCourse).isEqualTo(courseService.findByName("Spring Boot w Javie").get());
    }
    @Test
    public void shouldSaveIfDescriptionTooLong() {
        //given
        Category category = new Category("Java", "Kurs Java");
        categoryService.saveNewCategory(category);
        Subcategory subcategory = new Subcategory("Spring", "Kurs Spring", category);
        subcategoryService.saveNewSubcategory(subcategory);
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
        Course savedCourse = courseService.saveNewCourse(subcategory.getName(), course);

        //then
        assertThat(savedCourse.getDescription()).isEqualTo(course.getDescription());
    }
    @Test
    public void shouldNotSaveIfDescriptionTooLong() {
        //given
        Category category = new Category("Java", "Kurs Java");
        categoryService.saveNewCategory(category);
        Subcategory subcategory = new Subcategory("Spring", "Kurs Spring", category);
        subcategoryService.saveNewSubcategory(subcategory);
        String generatedTxt = RandomStringUtils.randomAlphanumeric(201);
        Course course = new Course(
                "Spring Boot w Javie",
                generatedTxt,
                2L,
                BigDecimal.valueOf(2000));

        //then
        Assertions.assertThrows(TooLongDescriptionException.class, () -> {
            courseService.saveNewCourse(subcategory.getName(), course);
        });
    }

    @Test
    public void shouldThrowExceptionIfNameAlreadyTaken() {
        //given
        Category category = new Category("Java", "Kurs Java");
        categoryService.saveNewCategory(category);
        Subcategory subcategory = new Subcategory("Spring", "Kurs Spring", category);
        subcategoryService.saveNewSubcategory(subcategory);
        Course course = new Course(
                "Spring Boot w Javie",
                "Kurs na temat Spring Boot w Javie",
                2L,
                BigDecimal.valueOf(2000));

        //then
        courseService.saveNewCourse(subcategory.getName(), course);
        Assertions.assertThrows(NameAlreadyTakenException.class, () -> {
           courseService.saveNewCourse(subcategory.getName(), course);
        });
    }

    @Test
    public void shouldCheckIfCourseIsAssignToSubcategory() {
        //given
        Category category = new Category("Java", "Kurs Java");
        categoryService.saveNewCategory(category);
        Subcategory subcategory = new Subcategory("Spring", "Kurs Spring", category);
        subcategoryService.saveNewSubcategory(subcategory);
        Course course = new Course("Kurs", "Opis", 250L, BigDecimal.valueOf(2000));
        courseService.saveNewCourse("Spring", course);

        //then
        assertThat(courseService.findByName("Kurs")).isPresent();
    }

}