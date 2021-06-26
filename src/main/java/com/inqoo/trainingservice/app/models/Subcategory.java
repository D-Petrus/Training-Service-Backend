package com.inqoo.trainingservice.app.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    @Column(length = 300)
    private String description;
    @ManyToOne
    private Category category;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Course> courseList = new ArrayList<>();

    public Subcategory(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public Subcategory() {
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

    public Category getCategory() {
        return category;
    }

    public void addCourse(Course course) {
        courseList.add(course);
    }
}
