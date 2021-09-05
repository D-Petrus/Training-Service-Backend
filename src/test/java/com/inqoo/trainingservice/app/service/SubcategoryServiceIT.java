package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.category.CategoryConverter;
import com.inqoo.trainingservice.app.category.CategoryDTO;
import com.inqoo.trainingservice.app.category.CategoryService;
import com.inqoo.trainingservice.app.course.CourseConverter;
import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.category.Category;
import com.inqoo.trainingservice.app.subcategory.SubCategoryConverter;
import com.inqoo.trainingservice.app.subcategory.SubCategoryDTO;
import com.inqoo.trainingservice.app.subcategory.Subcategory;
import com.inqoo.trainingservice.app.subcategory.SubcategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class SubcategoryServiceIT {
    @Autowired
    private SubcategoryService subcategoryService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CategoryService categoryService;

    private CategoryConverter categoryConverter = new CategoryConverter();
    private SubCategoryConverter subCategoryConverter = new SubCategoryConverter();

    @Test
    public void shouldReturnListOfSubCategory() {
        //given
        CategoryDTO category = new CategoryDTO(
                "test",
                "test",
                UUID.randomUUID()
        );
        categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        SubCategoryDTO subcategory1 = new SubCategoryDTO(
                "JavaBasic",
                "Podstawy Javy",
                UUID.randomUUID()
        );


        SubCategoryDTO subcategory2 = new SubCategoryDTO(
                "Java Advanced",
                "Java dla zaawansowanych",
                UUID.randomUUID()
        );
        //when
        Subcategory savedSubcategory1 = subcategoryService.saveNewSubcategory(subCategoryConverter.dtoToEntity(subcategory1), category.getName());
        Subcategory savedSubcategory2 = subcategoryService.saveNewSubcategory(subCategoryConverter.dtoToEntity(subcategory2), category.getName());

        //then
        assertThat(List.of(savedSubcategory2, savedSubcategory2)).isEqualTo(subcategoryService.getAllSubcategoryList());
    }


    @Test
    public void shouldReturnCategoryGivenByName() {
        //given
        CategoryDTO category = new CategoryDTO(
                "test",
                "test",
                UUID.randomUUID()
        );
        categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        SubCategoryDTO subcategory = new SubCategoryDTO(
                "JavaBasic",
                "Podstawy Javy",
                UUID.randomUUID()
        );

        //when
        Subcategory savedSubcategory = subcategoryService.saveNewSubcategory(subCategoryConverter.dtoToEntity(subcategory), category.getName());

        //then
        assertThat(savedSubcategory.getName()).isEqualTo(subcategory.getName());
    }

    @Test
    public void shouldSaveIfDescriptionOfCategoryIsTooLong() {
        //given
        CategoryDTO category = new CategoryDTO(
                "test",
                "test",
                UUID.randomUUID()
        );
        categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        String txt = "";
        int numberOfChars = 301;
        for (int i = 0; i < numberOfChars; i++) {
            txt += "a";
        }
        SubCategoryDTO subcategory = new SubCategoryDTO(
                "JavaBasic",
                txt,
                UUID.randomUUID()
        );
        //when
        Subcategory savedSubcategory = subcategoryService.saveNewSubcategory(subCategoryConverter.dtoToEntity(subcategory), category.getName());
        //then
        assertThat(savedSubcategory.getDescription()).isEqualTo(subcategory.getDescription());
    }

    @Test
    public void shouldThrowExceptionIfNameCategoryAlreadyTaken() {
        //given
        CategoryDTO category = new CategoryDTO(
                "test",
                "test",
                UUID.randomUUID()
        );
        categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        SubCategoryDTO subcategory = new SubCategoryDTO(
                "JavaBasic",
                "Podstawy Javy",
                UUID.randomUUID()
        );

        //then
        subcategoryService.saveNewSubcategory(subCategoryConverter.dtoToEntity(subcategory), category.getName());
        Assertions.assertThrows(NameAlreadyTakenException.class, () -> {
            subcategoryService.saveNewSubcategory(subCategoryConverter.dtoToEntity(subcategory), category.getName());
        });
    }

    @Test
    public void shouldCheckIfSubcategoryIsAssignToCategory() {
        //given
        CategoryDTO category = new CategoryDTO(
                "Java",
                "Java Courses",
                UUID.randomUUID()
        );
        categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        SubCategoryDTO subcategory = new SubCategoryDTO(
                "Spring",
                "Spring Courses",
                UUID.randomUUID()
        );

        //when
        subcategoryService.saveNewSubcategory(subCategoryConverter.dtoToEntity(subcategory), category.getName());

        //then
        Optional<String> first = categoryService.findByName("Java").map(Category::getSubcategoryList)
                .map(Collection::stream)
                .get()
                .map(Subcategory::getName)
                .filter(s -> s.equals("Sprin1"))
                .findFirst();

        assertThat(first).isPresent();
    }
    @Test
    public void shouldReturnSubCategoryNameList() throws Exception {
        //given
        CategoryDTO category = new CategoryDTO(
                "Java",
                "Java Courses",
                UUID.randomUUID()
        );
        categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        SubCategoryDTO subcategory = new SubCategoryDTO(
                "Spri1",
                "Spring Courses",
                UUID.randomUUID()
        );

        //when
        subcategoryService.saveNewSubcategory(subCategoryConverter.dtoToEntity(subcategory), category.getName());

        //then
        String content = this.mockMvc.perform(get("/subcategories/names"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(content).contains("Spring");
    }

}