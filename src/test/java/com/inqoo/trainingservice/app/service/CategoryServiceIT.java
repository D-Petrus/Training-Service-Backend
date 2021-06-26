package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.models.Category;
import com.inqoo.trainingservice.app.models.Subcategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CategoryServiceIT {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubcategoryService subcategoryService;

    @Test
    public void shouldReturnListOfCategory() {
        //given
        Category category1 = new Category(
                "JavaBasic",
                "Podstawy Javy");


        Category category2 = new Category(
                "Java Advanced",
                "Java dla zaawansowanych");
        //when
        Category savedCategory1 = categoryService.saveNewCategory(category1);
        Category savedCategory2 = categoryService.saveNewCategory(category2);

        //then
        assertThat(List.of(savedCategory1, savedCategory2)).isEqualTo(categoryService.getAllCategoryList());
    }

    @Test
    public void shouldCheckIfCatgoryIsSavedToDatabase() {
        //given
        Category category = new Category(
                "JavaBasic",
                "Podstawy Javy");

        //when
        Category savedCategory = categoryService.saveNewCategory(category);

        //then
        assertThat(savedCategory).isEqualTo(category);
    }

    @Test
    public void shouldReturnCategoryGivenByName() {
        //given
        Category category = new Category(
                "JavaBasic",
                "Podstawy Javy");

        //when
        Category savedCategory = categoryService.saveNewCategory(category);

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
        Category category = new Category(
                "JavaBasic", txt);
        //when
        Category savedCategory = categoryService.saveNewCategory(category);
        //then
        assertThat(savedCategory).isEqualTo(category);
    }

    @Test
    public void shouldThrowExceptionIfNameCategoryAlreadyTaken() {
        //given
        Category category = new Category(
                "JavaBasic",
                "Podstawy Javy");

        //then
        categoryService.saveNewCategory(category);
        Assertions.assertThrows(NameAlreadyTakenException.class, () -> {
            categoryService.saveNewCategory(category);
        });
    }
}