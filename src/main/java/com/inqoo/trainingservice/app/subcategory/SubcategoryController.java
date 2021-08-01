package com.inqoo.trainingservice.app.subcategory;

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

    @GetMapping
    private List<Subcategory> getList() {
        return subcategoryService.getAllSubcategoryList();
    }

    @PostMapping("{categoryName}")
    private Subcategory saveNew(@RequestBody Subcategory subcategory, @PathVariable String categoryName) {
        return subcategoryService.saveNewSubcategory(subcategory, categoryName);
    }

}
