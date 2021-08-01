package com.inqoo.trainingservice.app.subcategory;

import com.inqoo.trainingservice.app.category.CategoryNotFoundException;
import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.exception.TooLongDescriptionException;
import com.inqoo.trainingservice.app.category.Category;
import com.inqoo.trainingservice.app.category.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
            throw new CategoryNotFoundException("Category Not Found");
        }

    }

    private void validateInputs(Subcategory subcategory, String name) {
        if (!validateCharacters(subcategory.getDescription())) {
            throw new TooLongDescriptionException("Too long description");
        }
        if (subcategoryRepository.findByName(name).isPresent()) {
            throw new NameAlreadyTakenException("name already taken");
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

}
