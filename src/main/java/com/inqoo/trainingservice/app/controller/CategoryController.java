package com.inqoo.trainingservice.app.controller;

import com.inqoo.trainingservice.app.models.Category;
import com.inqoo.trainingservice.app.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main")
public class CategoryController {
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    private CategoryService categoryService;
    @GetMapping
    private List<Category> getList() {
        return categoryService.getAllCategoryList();
    }

    @PostMapping
    private Category saveNew(@RequestBody Category category) {
        return categoryService.saveNewCategory(category);
    }

}
