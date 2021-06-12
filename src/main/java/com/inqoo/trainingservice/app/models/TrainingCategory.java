package com.inqoo.trainingservice.app.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
@Entity
public class TrainingCategory {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        @Column(unique = true)
        private String nameCategory;
        @Column(length = 300)
        private String description;


    public TrainingCategory(String nameCategory, String description) {
        this.nameCategory = nameCategory;
        this.description = description;
    }

    public TrainingCategory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
