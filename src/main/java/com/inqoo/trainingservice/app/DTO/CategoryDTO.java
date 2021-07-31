package com.inqoo.trainingservice.app.DTO;

import java.util.UUID;

public class CategoryDTO {
    private final String name;
    private final String description;
    private final UUID categoryUUID;

    public CategoryDTO(String name, String description, UUID categoryUUID) {
        this.name = name;
        this.description = description;
        this.categoryUUID = categoryUUID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UUID getCategoryUUID() {
        return categoryUUID;
    }
}
