package com.inqoo.trainingservice.app.DTO;

import java.util.UUID;

public class CategoryDTO {
    private final String name;
    private final String description;
    private final UUID uuidCategory;

    public CategoryDTO(String name, String description, UUID uuidCategory) {
        this.name = name;
        this.description = description;
        this.uuidCategory = uuidCategory;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UUID getUuidCategory() {
        return uuidCategory;
    }
}
