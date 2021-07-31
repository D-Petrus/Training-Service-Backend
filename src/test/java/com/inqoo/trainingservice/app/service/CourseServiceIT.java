package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.DTO.CategoryDTO;
import com.inqoo.trainingservice.app.DTO.CourseDTO;
import com.inqoo.trainingservice.app.converter.CategoryConverter;
import com.inqoo.trainingservice.app.converter.CourseConverter;
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
import java.util.UUID;

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
    @Autowired
    private CategoryConverter categoryConverter;
    @Autowired
    private CourseConverter courseConverter;

    @Test
    public void shouldReturnListOfCourses() {
        //given
        CategoryDTO category = new CategoryDTO(
                "Java",
                "Kurs Java",
                UUID.randomUUID());
        categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        Subcategory subcategory = new Subcategory(
                "Spring",
                "Kurs Spring");
        subcategoryService.saveNewSubcategory(subcategory, category.getName());
        CourseDTO course1 = new CourseDTO(
                "Spring Boot w Javie",
                "Kurs na temat Spring Boot w Javie",
                2L,
                BigDecimal.valueOf(2000),
                UUID.randomUUID());

        CourseDTO course2 = new CourseDTO(
                "Hibernate w Javie",
                "Kurs na temat Hibernate w Javie",
                2L,
                BigDecimal.valueOf(3000),
                UUID.randomUUID());
        //when
        Course savedCourse1 = courseService.saveNewCourse(subcategory.getName(), courseConverter.dtoToEntity(course1));
        Course savedCourse2 = courseService.saveNewCourse(subcategory.getName(), courseConverter.dtoToEntity(course2));

        //then
        assertThat(List.of(savedCourse1,savedCourse2)).isEqualTo(courseService.getAllCoursesList());
    }

    @Test
    public void shouldCheckIfCourseIsSavedToDatabase() {
        //given
        CategoryDTO category = new CategoryDTO(
                "Java",
                "Kurs Java",
                UUID.randomUUID());
        categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        Subcategory subcategory = new Subcategory(
                "Spring",
                "Kurs Spring");
        subcategoryService.saveNewSubcategory(subcategory, category.getName());
        CourseDTO course = new CourseDTO(
                "Spring Boot w Javie",
                "Kurs na temat Spring Boot w Javie",
                2L,
                BigDecimal.valueOf(2000), UUID.randomUUID());

        //when
        Course savedCourse = courseService.saveNewCourse(subcategory.getName(), courseConverter.dtoToEntity(course));

        //then
        assertThat(savedCourse.getName()).isEqualTo(course.getName());
    }

    @Test
    public void shouldReturnCourseGivenByName() {
        //given
        CategoryDTO category = new CategoryDTO(
                "Java",
                "Kurs Java",
                UUID.randomUUID());
        categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        Subcategory subcategory = new Subcategory(
                "Spring",
                "Kurs Spring");
        subcategoryService.saveNewSubcategory(subcategory, category.getName());
        CourseDTO course = new CourseDTO(
                "Spring Boot w Javie",
                "Kurs na temat Spring Boot w Javie",
                2L,
                BigDecimal.valueOf(2000),
                UUID.randomUUID());

        //when
        Course savedCourse = courseService.saveNewCourse(subcategory.getName(), courseConverter.dtoToEntity(course));

        //then
        assertThat(savedCourse).isEqualTo(courseService.findByName("Spring Boot w Javie").get());
    }
    @Test
    public void shouldSaveIfDescriptionTooLong() {
        //given
        CategoryDTO category = new CategoryDTO(
                "Java",
                "Kurs Java",
                UUID.randomUUID());
        categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        Subcategory subcategory = new Subcategory(
                "Spring",
                "Kurs Spring");
        subcategoryService.saveNewSubcategory(subcategory, category.getName());
        String txt = "";
        int numberOfChars = 200;
        for (int i = 0; i < numberOfChars; i++) {
            txt += "a";
        }
        CourseDTO course = new CourseDTO(
                "Spring Boot w Javie",
                txt,
                2L,
                BigDecimal.valueOf(2000), UUID.randomUUID());
        //when
        Course savedCourse = courseService.saveNewCourse(subcategory.getName(), courseConverter.dtoToEntity(course));

        //then
        assertThat(savedCourse.getDescription()).isEqualTo(course.getDescription());
    }
    @Test
    public void shouldNotSaveIfDescriptionTooLong() {
        //given
        CategoryDTO category = new CategoryDTO(
                "Java",
                "Kurs Java",
                UUID.randomUUID());
        categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        Subcategory subcategory = new Subcategory(
                "Spring",
                "Kurs Spring");
        subcategoryService.saveNewSubcategory(subcategory, category.getName());
        String generatedTxt = RandomStringUtils.randomAlphanumeric(201);
        CourseDTO course = new CourseDTO(
                "Spring Boot w Javie",
                generatedTxt,
                2L,
                BigDecimal.valueOf(2000),
                UUID.randomUUID());

        //then
        Assertions.assertThrows(TooLongDescriptionException.class, () -> {
            courseService.saveNewCourse(subcategory.getName(), courseConverter.dtoToEntity(course));
        });
    }

    @Test
    public void shouldThrowExceptionIfNameAlreadyTaken() {
        //given
        CategoryDTO category = new CategoryDTO(
                "Java",
                "Kurs Java",
                UUID.randomUUID());
        categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        Subcategory subcategory = new Subcategory(
                "Spring",
                "Kurs Spring");
        subcategoryService.saveNewSubcategory(subcategory, category.getName());
        CourseDTO course = new CourseDTO(
                "Spring Boot w Javie",
                "Kurs na temat Spring Boot w Javie",
                2L,
                BigDecimal.valueOf(2000),
                UUID.randomUUID());

        //then
        courseService.saveNewCourse(subcategory.getName(), courseConverter.dtoToEntity(course));
        Assertions.assertThrows(NameAlreadyTakenException.class, () -> {
           courseService.saveNewCourse(subcategory.getName(), courseConverter.dtoToEntity(course));
        });
    }

    @Test
    public void shouldCheckIfCourseIsAssignToSubcategory() {
        //given
        CategoryDTO category = new CategoryDTO(
                "Java",
                "Kurs Java",
                UUID.randomUUID());
        categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        Subcategory subcategory = new Subcategory(
                "Spring",
                "Kurs Spring");
        subcategoryService.saveNewSubcategory(subcategory, category.getName());
        CourseDTO course = new CourseDTO(
                "Kurs",
                "Opis",
                250L,
                BigDecimal.valueOf(2000),
                UUID.randomUUID());
        courseService.saveNewCourse(
                "Spring",
                courseConverter.dtoToEntity(course));

        //then
        assertThat(courseService.findByName("Kurs")).isPresent();
    }

}