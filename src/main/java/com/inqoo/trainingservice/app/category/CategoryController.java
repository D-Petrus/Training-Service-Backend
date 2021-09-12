package com.inqoo.trainingservice.app.category;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    private Category saveNew(@RequestBody Category category) {
        log.info("Saving new category: "+category.getName());
        return categoryService.saveNewCategory(category);
    }

    @GetMapping
    private List<Category> getList() {
        log.info("Getting list of categories");
        return categoryService.getAllCategoryList();
    }

    @GetMapping("/names")
    private List<String> findByNameIn() {
        log.info("Getting category by name");
        return categoryService.getAllCategoryName();
    }
}
