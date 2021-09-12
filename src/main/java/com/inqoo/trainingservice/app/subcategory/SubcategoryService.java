package com.inqoo.trainingservice.app.subcategory;

import com.inqoo.trainingservice.app.category.CategoryNotFoundException;
import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.exception.TooLongDescriptionException;
import com.inqoo.trainingservice.app.category.Category;
import com.inqoo.trainingservice.app.category.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SubcategoryService {
    private SubcategoryRepository subcategoryRepository;
    private CategoryRepository categoryRepository;

    public SubcategoryService(SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    public Subcategory saveNewSubcategory(Subcategory subcategory, String categoryName) {
        validateInputs(subcategory, subcategory.getName());
        Optional<Category> category = categoryRepository.findByName(categoryName);

        if(category.isPresent()) {
            category.get().addSubcategory(subcategory);
            Category savedCategory = categoryRepository.save(category.get());
            return savedCategory.getSubcategoryList()
                    .stream()
                    .filter(subcategory1 -> subcategory1.getName().equals(subcategory.getName()))
                    .findFirst()
                    .get();
        } else {
            log.warn("Category not found!");
            throw new CategoryNotFoundException("Category not found!");
        }

    }

    private void validateInputs(Subcategory subcategory, String name) {
        if (!validateCharacters(subcategory.getDescription())) {
            log.info("Too long description of subcategory!");
            throw new TooLongDescriptionException("Too long description of subcategory!");
        }
        if (subcategoryRepository.findByName(name).isPresent()) {
            log.info("Subcategory name is already taken!");
            throw new NameAlreadyTakenException("Subcategory name is already taken!");
        }
    }

    public List<Subcategory> getAllSubcategoryList() {
        return subcategoryRepository.findAll();
    }

    Optional<Subcategory> findByName(String name) {
        return subcategoryRepository.findByName(name);
    }

    private boolean validateCharacters(String description) {
        int limitDescription = 301;
        return description.length() < limitDescription;
    }

    List<String> getAllSubcategoryName() {
        return subcategoryRepository.getAllSubcategoryName();
    }
}
