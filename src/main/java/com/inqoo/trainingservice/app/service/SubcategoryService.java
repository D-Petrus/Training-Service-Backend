package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.exception.TooLongDescriptionException;
import com.inqoo.trainingservice.app.models.Subcategory;
import com.inqoo.trainingservice.app.repository.SubcategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {
    private SubcategoryRepository subcategoryRepository;

    public SubcategoryService(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    public Subcategory saveNewSubcategory(Subcategory subcategory) {
        validateInputs(subcategory, subcategory.getName());
        return subcategoryRepository.save(subcategory);
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
