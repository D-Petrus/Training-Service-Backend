package com.inqoo.trainingservice.app.category;

import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.exception.TooLongDescriptionException;
import com.inqoo.trainingservice.app.subcategory.Subcategory;
import com.inqoo.trainingservice.app.subcategory.SubcategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;

    public CategoryService(CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    public Category saveNewCategory(Category category) {
        validateInputs(category, category.getName());
        return categoryRepository.save(category);
    }

    private void validateInputs(Category category, String name) {
        if (!validateCharacters(category.getDescription())) {
            log.info("Too long description of category!");
            throw new TooLongDescriptionException("Too long description of category!");
        }
        if (categoryRepository.findByName(name).isPresent()) {
            log.info("Category name is already taken!");
            throw new NameAlreadyTakenException("Category name is already taken!");
        }
    }

    public List<Category> getAllCategoryList() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findByName(String name) {
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
        log.warn("Subcategory is not assigned to category!");
        return false;
    }
    List<String> getAllCategoryName() {
        return categoryRepository.getAllCategoryName();
    }
}
