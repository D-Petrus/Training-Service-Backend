package com.inqoo.trainingservice.app.subcategory;

import java.util.UUID;

public class SubCategoryDTO {
    private final String name;
    private final String description;
    private final UUID subCategoryUUID;

    public SubCategoryDTO(String name, String description, UUID subCategoryUUID) {
        this.name = name;
        this.description = description;
        this.subCategoryUUID = subCategoryUUID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UUID getSubCategoryUUID() {
        return subCategoryUUID;
    }
}
