package com.inqoo.trainingservice.app.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    @Column(length = 300)
    private String description;

    @OneToMany(cascade = {CascadeType.MERGE , CascadeType.PERSIST})
    private final List<Subcategory> subcategoryList = new ArrayList<>();


    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Category() {
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

    public void addSubcategory(Subcategory subcategory) {
        subcategoryList.add(subcategory);
    }

    public List<Subcategory> getSubcategoryList() {
        return subcategoryList;
    }
}
