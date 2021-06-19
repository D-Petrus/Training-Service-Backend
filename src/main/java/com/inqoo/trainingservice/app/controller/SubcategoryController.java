package com.inqoo.trainingservice.app.controller;

import com.inqoo.trainingservice.app.models.Subcategory;
import com.inqoo.trainingservice.app.service.SubcategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class SubcategoryController {
    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    private SubcategoryService subcategoryService;
    @GetMapping
    private List<Subcategory> getList() {
        return subcategoryService.getAllSubcategoryList();
    }

    @PostMapping
    private Subcategory saveNew(@RequestBody Subcategory subcategory) {
        return subcategoryService.saveNewSubcategory(subcategory);
    }

}
