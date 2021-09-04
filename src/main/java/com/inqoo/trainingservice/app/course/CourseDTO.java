package com.inqoo.trainingservice.app.course;

import java.math.BigDecimal;
import java.util.UUID;

public class CourseDTO {
    private final String name;
    private final String description;
    private final UUID courseUUID;
    private final int duration;
    private BigDecimal price;

    public CourseDTO(String name, String description, int duration, BigDecimal price, UUID courseUUID) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
        this.courseUUID = courseUUID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UUID getCourseUUID() {
        return courseUUID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getDuration() {
        return duration;
    }
}
