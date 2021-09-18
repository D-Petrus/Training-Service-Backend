package com.inqoo.trainingservice.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.inqoo.trainingservice.app.category.CategoryDTO;
import com.inqoo.trainingservice.app.course.*;
import com.inqoo.trainingservice.app.category.CategoryConverter;
import com.inqoo.trainingservice.app.category.CategoryService;
import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.exception.TooLongDescriptionException;
import com.inqoo.trainingservice.app.subcategory.SubCategoryConverter;
import com.inqoo.trainingservice.app.subcategory.SubCategoryDTO;
import com.inqoo.trainingservice.app.subcategory.SubcategoryService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class CourseServiceIT {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubcategoryService subcategoryService;
    @Autowired
    private MockMvc mockMvc;

    private CategoryConverter categoryConverter = new CategoryConverter();
    private CourseConverter courseConverter = new CourseConverter();
    private SubCategoryConverter subCategoryConverter = new SubCategoryConverter();


    @Test
    public void shouldReturnListOfCourses() {
        //given
        CategoryDTO category = new CategoryDTO(
                "Java",
                "Kurs Java",
                UUID.randomUUID());
        categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        SubCategoryDTO subcategory = new SubCategoryDTO(
                "Spring",
                "Kurs Spring",
                UUID.randomUUID()
        );
        subcategoryService.saveNewSubcategory(subCategoryConverter.dtoToEntity(subcategory), category.getName());
        CourseDTO course1 = new CourseDTO(
                "Spring Boot w Javie",
                "Kurs na temat Spring Boot w Javie",
                2,
                BigDecimal.valueOf(2000),
                UUID.randomUUID());

        CourseDTO course2 = new CourseDTO(
                "Hibernate w Javie",
                "Kurs na temat Hibernate w Javie",
                2,
                BigDecimal.valueOf(3000),
                UUID.randomUUID());
        //when
        Course savedCourse1 = courseService.saveNewCourse(subcategory.getName(), courseConverter.dtoToEntity(course1));
        Course savedCourse2 = courseService.saveNewCourse(subcategory.getName(), courseConverter.dtoToEntity(course2));

        //then
        assertThat(List.of(savedCourse1, savedCourse2)).isEqualTo(courseService.getAllCoursesList());
    }

    @Test
    public void shouldCheckIfCourseIsSavedToDatabase() {
        //given
        CategoryDTO category = new CategoryDTO(
                "Java",
                "Kurs Java",
                UUID.randomUUID());
        categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        SubCategoryDTO subcategory = new SubCategoryDTO(
                "Spring",
                "Kurs Spring",
                UUID.randomUUID()
        );
        subcategoryService.saveNewSubcategory(subCategoryConverter.dtoToEntity(subcategory), category.getName());
        CourseDTO course = new CourseDTO(
                "Spring Boot w Javie",
                "Kurs na temat Spring Boot w Javie",
                2,
                BigDecimal.valueOf(2000),
                UUID.randomUUID()
        );

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
        SubCategoryDTO subcategory = new SubCategoryDTO(
                "Spring",
                "Kurs Spring",
                UUID.randomUUID()
        );
        subcategoryService.saveNewSubcategory(subCategoryConverter.dtoToEntity(subcategory), category.getName());
        CourseDTO course = new CourseDTO(
                "Spring Boot w Javie",
                "Kurs na temat Spring Boot w Javie",
                2,
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
        SubCategoryDTO subcategory = new SubCategoryDTO(
                "Spring",
                "Kurs Spring",
                UUID.randomUUID()
        );
        subcategoryService.saveNewSubcategory(subCategoryConverter.dtoToEntity(subcategory), category.getName());
        String txt = "";
        int numberOfChars = 200;
        for (int i = 0; i < numberOfChars; i++) {
            txt += "a";
        }
        CourseDTO course = new CourseDTO(
                "Spring Boot w Javie",
                txt,
                2,
                BigDecimal.valueOf(2000),
                UUID.randomUUID());
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
        SubCategoryDTO subcategory = new SubCategoryDTO(
                "Spring",
                "Kurs Spring",
                UUID.randomUUID()
        );
        subcategoryService.saveNewSubcategory(subCategoryConverter.dtoToEntity(subcategory), category.getName());
        String generatedTxt = RandomStringUtils.randomAlphanumeric(201);
        CourseDTO course = new CourseDTO(
                "Spring Boot w Javie",
                generatedTxt,
                2,
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
        SubCategoryDTO subcategory = new SubCategoryDTO(
                "Spring",
                "Kurs Spring",
                UUID.randomUUID()
        );
        subcategoryService.saveNewSubcategory(subCategoryConverter.dtoToEntity(subcategory), category.getName());
        CourseDTO course = new CourseDTO(
                "Spring Boot w Javie",
                "Kurs na temat Spring Boot w Javie",
                2,
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
        SubCategoryDTO subcategory = new SubCategoryDTO(
                "Spring",
                "Kurs Spring",
                UUID.randomUUID()
        );
        subcategoryService.saveNewSubcategory(subCategoryConverter.dtoToEntity(subcategory), category.getName());
        CourseDTO course = new CourseDTO(
                "Kurs",
                "Opis",
                250,
                BigDecimal.valueOf(2000),
                UUID.randomUUID());
        courseService.saveNewCourse(
                "Spring",
                courseConverter.dtoToEntity(course));

        //then
        assertThat(courseService.findByName("Kurs")).isPresent();
    }

    @Test
    public void shouldReturnCourseNamesFromGivenSubcategory() throws Exception {
        //given
        CategoryDTO category = thereIsCategory("Java", "Kurs Java");
        SubCategoryDTO subcategory1 = thereIsSubcategory("Spring", "Kurs Spring", category.getName());
        SubCategoryDTO subcategory2 = thereIsSubcategory("Hibernate", "Kurs Hibernate", category.getName());
        CourseDTO course1 = thereIsCourse("Kurs", "Opis", 250, BigDecimal.valueOf(2000), subcategory1.getName());
        CourseDTO course2 = thereIsCourse("Kurs2", "Opis2", 250, BigDecimal.valueOf(2000), subcategory1.getName());
        CourseDTO course3 = thereIsCourse("Kurs3", "Opis3", 250, BigDecimal.valueOf(2000), subcategory2.getName());
        String requestJson = parameterAsJson(Arrays.asList("Spring", "Hibernate"));

        //then
        String content = this.mockMvc
                .perform(
                        get("/courses/Spring"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(content).contains(course1.getName());
        assertThat(content).contains(course2.getName());
    }

    private CategoryDTO thereIsCategory(String name, String description) {
        CategoryDTO category = new CategoryDTO(name, description, UUID.randomUUID());
        categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        return category;
    }

    private SubCategoryDTO thereIsSubcategory(String name, String description, String categoryName) {
        SubCategoryDTO subcategory = new SubCategoryDTO(name, description, UUID.randomUUID());
        subcategoryService.saveNewSubcategory(subCategoryConverter.dtoToEntity(subcategory), categoryName);
        return subcategory;
    }

    private CourseDTO thereIsCourse(String name, String description, int duration, BigDecimal price, String subcategoryName) {
        CourseDTO course = new CourseDTO(name, description, duration, price, UUID.randomUUID());
        courseService.saveNewCourse(subcategoryName, courseConverter.dtoToEntity(course));
        return course;
    }

    String parameterAsJson(List<String> parameters) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        CourseNamesList courseNamesList = new CourseNamesList();
        parameters.forEach(courseNamesList::add);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(courseNamesList);
    }
}
