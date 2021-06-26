package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.models.Category;
import com.inqoo.trainingservice.app.models.RelationCategorySubCategory;
import com.inqoo.trainingservice.app.models.Subcategory;
import com.inqoo.trainingservice.app.repository.CategoryRepository;
import com.inqoo.trainingservice.app.repository.RelationCategorySubCategoryRepository;
import com.inqoo.trainingservice.app.repository.SubcategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class RelationCategorySubCategoryService {
    private RelationCategorySubCategoryRepository relationCategorySubCategoryRepository;
    private CategoryRepository categoryRepository;
    private SubcategoryRepository subcategoryRepository;

    public RelationCategorySubCategoryService(
            RelationCategorySubCategoryRepository relationCategorySubCategoryRepository,
            SubcategoryRepository subcategoryRepository,
            CategoryRepository categoryRepository
    ) {
        this.relationCategorySubCategoryRepository = relationCategorySubCategoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<RelationCategorySubCategory> findAll() {
        return null;
    }

    public boolean saveRelationCategorySubCategory(Long category_id, Long subcategory_id) {
        Optional<Category> foundedCategory = categoryRepository.findById(category_id);
        Optional<Subcategory> foundedSubcategory = subcategoryRepository.findById(subcategory_id);
        if (foundedCategory.isPresent() && foundedSubcategory.isPresent()) {

            return true;
        }
        return false;
    }
}
