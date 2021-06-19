package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.exception.TooLongDescriptionException;
import com.inqoo.trainingservice.app.models.Category;
import com.inqoo.trainingservice.app.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveNewCategory(Category category) {
        validateInputs(category, category.getName());
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

}
