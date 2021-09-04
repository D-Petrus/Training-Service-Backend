package com.inqoo.trainingservice.app.subcategory;

public class SubcategoryNotFoundException extends RuntimeException {
    public SubcategoryNotFoundException() {
        super("Subcategory Not Found");
    }
}
