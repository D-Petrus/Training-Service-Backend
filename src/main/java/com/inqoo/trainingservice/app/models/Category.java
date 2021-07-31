package com.inqoo.trainingservice.app.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    @Column(length = 300)
    private String description;
    @Column
    private UUID uuidCategory;

    @OneToMany(cascade = {CascadeType.MERGE , CascadeType.PERSIST})
    private final List<Subcategory> subcategoryList = new ArrayList<>();


    public Category(String name, String description, UUID uuidCategory) {
        this.name = name;
        this.description = description;
        this.uuidCategory = uuidCategory;
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

    public UUID getUuidCategory() {
        return uuidCategory;
    }

    public void setUuidCategory(UUID uuidCategory) {
        this.uuidCategory = uuidCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id && Objects.equals(name, category.name) && Objects.equals(description, category.description) && Objects.equals(uuidCategory, category.uuidCategory) && Objects.equals(subcategoryList, category.subcategoryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, uuidCategory, subcategoryList);
    }
}
