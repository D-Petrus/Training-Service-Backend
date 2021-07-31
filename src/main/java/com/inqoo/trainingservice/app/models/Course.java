package com.inqoo.trainingservice.app.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    @Column(length = 200)
    private String description;
    private Long duration;
    private BigDecimal price;
    @Column
    private UUID uuidCourse;
    
    public Course(String name, String description, Long duration, BigDecimal price, UUID uuidCourse) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
        this.uuidCourse = uuidCourse;
    }

    public Course() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuidCourse() {
        return uuidCourse;
    }

    public void setUuidCourse(UUID uuidCourse) {
        this.uuidCourse = uuidCourse;
    }
}
