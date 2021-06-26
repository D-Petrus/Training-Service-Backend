package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.exception.TooLongDescriptionException;
import com.inqoo.trainingservice.app.models.Category;
import com.inqoo.trainingservice.app.models.RelationCategorySubCategory;
import com.inqoo.trainingservice.app.models.Subcategory;
import com.inqoo.trainingservice.app.repository.CategoryRepository;
import com.inqoo.trainingservice.app.repository.RelationCategorySubCategoryRepository;
import com.inqoo.trainingservice.app.repository.SubcategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;

    public CategoryService(CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    public Category saveNewCategory(Category category) {
//        validateInputs(category, category.getName());
        return categoryRepository.save(category);
    }

    private void validateInputs(Category category, String name) {
        if (!validateCharacters(category.getDescription())) {
            throw new TooLongDescriptionException("Too long description");
        }
        if (categoryRepository.findByName(name).isPresent()) {
            throw new NameAlreadyTakenException("name already taken");
        }
    }

    public List<Category> getAllCategoryList() {
        return categoryRepository.findAll();
    }

    Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    private boolean validateCharacters(String description) {
        int limitDescription = 301;
        return description.length() < limitDescription;
    }

    public boolean saveNewRelation(Long category_id, Long subcategory_id) {
        Optional<Category> categoryFounded = categoryRepository.findById(category_id);
        Optional<Subcategory> subcategoryFounded = subcategoryRepository.findById(subcategory_id);
        if (categoryFounded.isPresent() && subcategoryFounded.isPresent()) {
            categoryFounded.get().addSubcategory(subcategoryFounded.get());
            categoryRepository.save(categoryFounded.get());
            return true;
        }
        return false;
    }

}
