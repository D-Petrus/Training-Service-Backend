package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.models.Subcategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
class SubcategoryServiceIT {
    @Autowired
    private SubcategoryService trainingCategoryService;

    @Test
    public void shouldReturnListOfCategory() {
        //given
        Subcategory subcategory1 = new Subcategory(
                "JavaBasic",
                "Podstawy Javy");


        Subcategory subcategory2 = new Subcategory(
                "Java Advanced",
                "Java dla zaawansowanych");
        //when
        Subcategory savedSubcategory1 = trainingCategoryService.saveNewSubcategory(subcategory1);
        Subcategory savedSubcategory2 = trainingCategoryService.saveNewSubcategory(subcategory2);

        //then
        assertThat(List.of(savedSubcategory1,savedSubcategory2)).isEqualTo(trainingCategoryService.getAllSubcategoryList());
    }

    @Test
    public void shouldCheckIfCatgoryIsSavedToDatabase() {
        //given
        Subcategory subcategory = new Subcategory(
                "JavaBasic",
                "Podstawy Javy");

        //when
        Subcategory savedSubcategory = trainingCategoryService.saveNewSubcategory(subcategory);

        //then
        assertThat(savedSubcategory).isEqualTo(subcategory);
    }
    @Test
    public void shouldReturnCategoryGivenByName() {
        //given
        Subcategory subcategory = new Subcategory(
                "JavaBasic",
                "Podstawy Javy");

        //when
        Subcategory savedSubcategory = trainingCategoryService.saveNewSubcategory(subcategory);

        //then
        assertThat(savedSubcategory).isEqualTo(trainingCategoryService.findByName("JavaBasic").get());
    }
    @Test
    public void shouldSaveIfDescriptionOfCategoryIsTooLong() {
        //given
        String txt = "";
        int numberOfChars = 300;
        for (int i = 0; i < numberOfChars; i++) {
            txt += "a";
        }
        Subcategory subcategory = new Subcategory(
                "JavaBasic", txt);
        //when
        Subcategory savedSubcategory = trainingCategoryService.saveNewSubcategory(subcategory);
        //then
        assertThat(savedSubcategory).isEqualTo(subcategory);
    }
    @Test
    public void shouldThrowExceptionIfNameCategoryAlreadyTaken() {
        //given
        Subcategory subcategory = new Subcategory(
                "JavaBasic",
                "Podstawy Javy");

        //then
        trainingCategoryService.saveNewSubcategory(subcategory);
        Assertions.assertThrows(NameAlreadyTakenException.class, () -> {
            trainingCategoryService.saveNewSubcategory(subcategory);
        });
    }

}