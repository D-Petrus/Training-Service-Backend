package com.inqoo.trainingservice.app.converter;

import com.inqoo.trainingservice.app.DTO.CategoryDTO;
import com.inqoo.trainingservice.app.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public CategoryDTO entityToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        return categoryDTO;
    }

    public Category dtoToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }
}
