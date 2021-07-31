package com.inqoo.trainingservice.app.DTO;

import java.math.BigDecimal;
import java.util.UUID;

public class CourseDTO {
    private final String name;
    private final String description;
    private final UUID uuidCourse;
    private final Long duration;
    private BigDecimal price;

    public CourseDTO(String name, String description,  Long duration, BigDecimal price, UUID uuidCourse) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
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

    public BigDecimal getPrice() {
        return price;
    }

    public Long getDuration() {
        return duration;
    }
}
