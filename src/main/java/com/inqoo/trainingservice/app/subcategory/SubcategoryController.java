package com.inqoo.trainingservice.app.subcategory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/subcategories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
class SubcategoryController {
    private final SubcategoryService subcategoryService;

    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @PostMapping("/{categoryName}")
    private Subcategory saveNew(@RequestBody Subcategory subcategory, @PathVariable String categoryName) {
        log.info("Saving new subcategory: "+subcategory.getName());
        return subcategoryService.saveNewSubcategory(subcategory, categoryName);
    }

    @GetMapping
    private List<Subcategory> getList() {
        log.info("Getting list of subcategories");
        return subcategoryService.getAllSubcategoryList();
    }

    @GetMapping("/names")
    private List<String> findByNameIn() {
        log.info("Getting subcategory by name");
        return subcategoryService.getAllSubcategoryName();
    }


}
