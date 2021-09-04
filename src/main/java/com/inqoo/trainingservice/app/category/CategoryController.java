package com.inqoo.trainingservice.app.category;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    private Category saveNew(@RequestBody Category category) {
        return categoryService.saveNewCategory(category);
    }

    @GetMapping
    private List<Category> getList() {
        return categoryService.getAllCategoryList();
    }

    @GetMapping("/names")
    private List<String> findByNameIn() {
        return categoryService.getAllCategoryName();
    }
}
