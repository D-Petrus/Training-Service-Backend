package com.inqoo.trainingservice.app.subcategory;

import com.inqoo.trainingservice.app.category.Category;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class RelationCategorySubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Category category;
    private Subcategory subcategory;


    public RelationCategorySubCategory(Category category, Subcategory subcategory) {
        this.category = category;
        this.subcategory = subcategory;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }
}
