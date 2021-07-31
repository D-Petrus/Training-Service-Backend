package com.inqoo.trainingservice.app.DTO;

import java.util.UUID;

public class CourseDTO {
    private final String name;
    private final String description;
    private final UUID uuidCourse;

    public CourseDTO(String name, String description, UUID uuidCourse) {
        this.name = name;
        this.description = description;
        this.uuidCourse = uuidCourse;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UUID getUuidCourse() {
        return uuidCourse;
    }
}
