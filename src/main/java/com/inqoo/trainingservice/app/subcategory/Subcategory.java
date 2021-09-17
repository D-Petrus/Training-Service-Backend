package com.inqoo.trainingservice.app.subcategory;

import com.inqoo.trainingservice.app.course.Course;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(unique = true)
    private String name;
    @Column(length = 300)
    private String description;
    @Column
    private UUID subCategoryUUID;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private final List<Course> courseList = new ArrayList<>();

    public Subcategory(String name, String description, UUID subCategoryUUID) {
        this.name = name;
        this.description = description;
        this.subCategoryUUID = subCategoryUUID;
    }

    public Subcategory() {
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }

    public UUID getSubCategoryUUID() {
        return subCategoryUUID;
    }

    public void setSubCategoryUUID(UUID subCategoryUUID) {
        this.subCategoryUUID = subCategoryUUID;
    }
}
