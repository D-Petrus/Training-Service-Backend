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
        Category category = new Category( "test", "test", uuidCategory);
        categoryService.saveNewCategory(category);
        Subcategory subcategory1 = new Subcategory(
                "JavaBasic",
                "Podstawy Javy", category);


        Subcategory subcategory2 = new Subcategory(
                "Java Advanced",
                "Java dla zaawansowanych", category);
        //when
        Subcategory savedSubcategory1 = subcategoryService.saveNewSubcategory(subcategory1);
        Subcategory savedSubcategory2 = subcategoryService.saveNewSubcategory(subcategory2);

        //then
        assertThat(List.of(savedSubcategory1,savedSubcategory2)).isEqualTo(subcategoryService.getAllSubcategoryList());
    }


    @Test
    public void shouldReturnCategoryGivenByName() {
        //given
        Category category = new Category( "test", "test", uuidCategory);
        categoryService.saveNewCategory(category);
        Subcategory subcategory = new Subcategory(
                "JavaBasic",
                "Podstawy Javy", category);

        //when
        Subcategory savedSubcategory = subcategoryService.saveNewSubcategory(subcategory);

        //then
        assertThat(savedSubcategory.getName()).isEqualTo(subcategory.getName());
    }
    @Test
    public void shouldSaveIfDescriptionOfCategoryIsTooLong() {
        //given
        Category category = new Category( "test", "test", uuidCategory);
        categoryService.saveNewCategory(category);
        String txt = "";
        int numberOfChars = 300;
        for (int i = 0; i < numberOfChars; i++) {
            txt += "a";
        }
        Subcategory subcategory = new Subcategory(
                "JavaBasic", txt, category);
        //when
        Subcategory savedSubcategory = subcategoryService.saveNewSubcategory(subcategory);
        //then
        assertThat(savedSubcategory.getDescription()).isEqualTo(subcategory.getDescription());
    }
    @Test
    public void shouldThrowExceptionIfNameCategoryAlreadyTaken() {
        //given
        Category category = new Category( "test", "test", uuidCategory);
        categoryService.saveNewCategory(category);
        Subcategory subcategory = new Subcategory(
                "JavaBasic",
                "Podstawy Javy", category);

        //then
        subcategoryService.saveNewSubcategory(subcategory);
        Assertions.assertThrows(NameAlreadyTakenException.class, () -> {
            subcategoryService.saveNewSubcategory(subcategory);
        });
    }

    @Test
    public void shouldCheckIfSubcategoryIsAssignToCategory() {
        //given
        Category category = new Category("Java", "Java Courses", uuidCategory);
        categoryService.saveNewCategory(category);
        Subcategory subcategory = new Subcategory("Spring", "Spring Courses", category);

        //when
        subcategoryService.saveNewSubcategory(subcategory);

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