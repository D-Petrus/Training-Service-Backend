package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.models.Category;
import com.inqoo.trainingservice.app.models.Subcategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
class SubcategoryServiceIT {
    @Autowired
    private SubcategoryService subcategoryService;
    @Autowired
    private CategoryService categoryService;

    @Test
    public void shouldReturnListOfCategory() {
        //given
        Category category = new Category( "test", "test", UUID.randomUUID());
        categoryService.saveNewCategory(category);
        Subcategory subcategory1 = new Subcategory(
                "JavaBasic",
                "Podstawy Javy");


        Subcategory subcategory2 = new Subcategory(
                "Java Advanced",
                "Java dla zaawansowanych");
        //when
        Subcategory savedSubcategory1 = subcategoryService.saveNewSubcategory(subcategory1, category.getName());
        Subcategory savedSubcategory2 = subcategoryService.saveNewSubcategory(subcategory2, category.getName());

        //then
        assertThat(List.of(savedSubcategory1,savedSubcategory2)).isEqualTo(subcategoryService.getAllSubcategoryList());
    }


    @Test
    public void shouldReturnCategoryGivenByName() {
        //given
        Category category = new Category( "test", "test", UUID.randomUUID());
        categoryService.saveNewCategory(category);
        Subcategory subcategory = new Subcategory(
                "JavaBasic",
                "Podstawy Javy");

        //when
        Subcategory savedSubcategory = subcategoryService.saveNewSubcategory(subcategory, category.getName());

        //then
        assertThat(savedSubcategory.getName()).isEqualTo(subcategory.getName());
    }
    @Test
    public void shouldSaveIfDescriptionOfCategoryIsTooLong() {
        //given
        Category category = new Category( "test", "test", UUID.randomUUID());
        categoryService.saveNewCategory(category);
        String txt = "";
        int numberOfChars = 300;
        for (int i = 0; i < numberOfChars; i++) {
            txt += "a";
        }
        Subcategory subcategory = new Subcategory(
                "JavaBasic", txt);
        //when
        Subcategory savedSubcategory = subcategoryService.saveNewSubcategory(subcategory, category.getName());
        //then
        assertThat(savedSubcategory.getDescription()).isEqualTo(subcategory.getDescription());
    }
    @Test
    public void shouldThrowExceptionIfNameCategoryAlreadyTaken() {
        //given
        Category category = new Category( "test", "test", UUID.randomUUID());
        categoryService.saveNewCategory(category);
        Subcategory subcategory = new Subcategory(
                "JavaBasic",
                "Podstawy Javy");

        //then
        subcategoryService.saveNewSubcategory(subcategory, category.getName());
        Assertions.assertThrows(NameAlreadyTakenException.class, () -> {
            subcategoryService.saveNewSubcategory(subcategory, category.getName());
        });
    }

    @Test
    public void shouldCheckIfSubcategoryIsAssignToCategory() {
        //given
        Category category = new Category("Java", "Java Courses", UUID.randomUUID());
        categoryService.saveNewCategory(category);
        Subcategory subcategory = new Subcategory("Spring", "Spring Courses");

        //when
        subcategoryService.saveNewSubcategory(subcategory, category.getName());

        //then
        Optional<String> first = categoryService.findByName("Java").map(Category::getSubcategoryList)
                .map(Collection::stream)
                .get()
                .map(Subcategory::getName)
                .filter(s -> s.equals("Spring"))
                .findFirst();

        assertThat(first).isPresent();
    }

}