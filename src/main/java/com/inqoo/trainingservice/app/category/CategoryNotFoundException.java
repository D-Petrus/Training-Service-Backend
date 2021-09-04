package com.inqoo.trainingservice.app.category;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException() {
        super("Category Not Found");
    }
}
