package com.inqoo.trainingservice.app.category;

import com.inqoo.trainingservice.app.subcategory.Subcategory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(unique = true)
    private String name;
    @Column(length = 300)
    private String description;
    @Column
    private UUID categoryUUID;

    @OneToMany(cascade = {CascadeType.MERGE , CascadeType.PERSIST})
    private final List<Subcategory> subcategoryList = new ArrayList<>();


    public Category(String name, String description, UUID categoryUUID) {
        this.name = name;
        this.description = description;
        this.categoryUUID = categoryUUID;
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

    public UUID getCategoryUUID() {
        return categoryUUID;
    }

    public void setCategoryUUID(UUID categoryUUID) {
        this.categoryUUID = categoryUUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id && Objects.equals(name, category.name) && Objects.equals(description, category.description) && Objects.equals(categoryUUID, category.categoryUUID) && Objects.equals(subcategoryList, category.subcategoryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, categoryUUID, subcategoryList);
    }
}
