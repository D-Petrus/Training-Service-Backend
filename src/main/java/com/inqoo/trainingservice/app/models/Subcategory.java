package com.inqoo.trainingservice.app.models;

import javax.persistence.*;

@Entity
public class Subcategory {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        @Column(unique = true)
        private String name;
        @Column(length = 300)
        private String description;


    public Subcategory(String name, String description) {
        this.name = name;
        this.description = description;
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
}