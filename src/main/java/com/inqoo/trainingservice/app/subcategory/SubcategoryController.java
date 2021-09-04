package com.inqoo.trainingservice.app.subcategory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcategories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
class SubcategoryController {
    private SubcategoryService subcategoryService;

    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @PostMapping("/{categoryName}")
    private Subcategory saveNew(@RequestBody Subcategory subcategory, @PathVariable String categoryName) {
        return subcategoryService.saveNewSubcategory(subcategory, categoryName);
    }

    @GetMapping
    private List<Subcategory> getList() {
        return subcategoryService.getAllSubcategoryList();
    }

    @GetMapping("/names")
    private List<String> findByNameIn() {
        return subcategoryService.getAllSubcategoryName();
    }


}
