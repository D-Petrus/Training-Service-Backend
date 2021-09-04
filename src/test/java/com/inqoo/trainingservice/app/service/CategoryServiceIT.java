package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.category.CategoryDTO;
import com.inqoo.trainingservice.app.category.CategoryConverter;
import com.inqoo.trainingservice.app.category.CategoryService;
import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.category.Category;
import com.inqoo.trainingservice.app.subcategory.SubcategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class CategoryServiceIT {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubcategoryService subcategoryService;
    @Autowired
    private MockMvc mockMvc;

    private CategoryConverter categoryConverter = new CategoryConverter();

    @Test
    public void shouldReturnListOfCategory() {
        //given
        CategoryDTO category1 = new CategoryDTO(
                "JavaBasic",
                "Podstawy Javy",
                UUID.randomUUID());


        CategoryDTO category2 = new CategoryDTO(
                "Java Advanced",
                "Java dla zaawansowanych",
                UUID.randomUUID());
        //when
        Category savedCategory1 = categoryService.saveNewCategory(categoryConverter.dtoToEntity(category1));
        Category savedCategory2 = categoryService.saveNewCategory(categoryConverter.dtoToEntity(category2));

        //then
        assertThat(List.of(savedCategory1, savedCategory2)).isEqualTo(categoryService.getAllCategoryList());
    }

    @Test
    public void shouldCheckIfCatgoryIsSavedToDatabase() {
        //given
        CategoryDTO category = new CategoryDTO(
                "JavaBasic",
                "Podstawy Javy",
                UUID.randomUUID());

        //when
        Category savedCategory = categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));

        //then
        assertThat(savedCategory.getName()).isEqualTo(category.getName());
    }

    @Test
    public void shouldReturnCategoryGivenByName() {
        //given
        CategoryDTO category = new CategoryDTO(
                "JavaBasic",
                "Podstawy Javy",
                UUID.randomUUID());

        //when
        Category savedCategory = categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));

        //then
        assertThat(savedCategory).isEqualTo(categoryService.findByName("JavaBasic").get());
    }

    @Test
    public void shouldSaveIfDescriptionOfCategoryIsTooLong() {
        //given
        String txt = "";
        int numberOfChars = 300;
        for (int i = 0; i < numberOfChars; i++) {
            txt += "a";
        }
        CategoryDTO category = new CategoryDTO(
                "JavaBasic",
                txt,
                UUID.randomUUID());
        //when
        Category savedCategory = categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        //then
        assertThat(savedCategory.getName()).isEqualTo(category.getName());
    }

    @Test
    public void shouldThrowExceptionIfNameCategoryAlreadyTaken() {
        //given
        CategoryDTO category = new CategoryDTO(
                "JavaBasic",
                "Podstawy Javy",
                UUID.randomUUID());

        //then
        categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        Assertions.assertThrows(NameAlreadyTakenException.class, () -> {
            categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        });
    }
    @Test
    public void shouldReturnCategoryNameList() throws Exception {
        //given
        CategoryDTO category = new CategoryDTO(
                "JavaBasic",
                "Podstawy Javy",
                UUID.randomUUID());
        //when
        categoryService.saveNewCategory(categoryConverter.dtoToEntity(category));
        //then
        String content = this.mockMvc.perform(get("/categories/names"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(content).contains("JavaBasic");
    }
}