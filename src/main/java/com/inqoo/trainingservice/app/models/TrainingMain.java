package com.inqoo.trainingservice.app.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
@Entity
public class TrainingMain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String nameMain;
    @Column(length = 300)
    private String description;


    public TrainingMain(String nameMain, String description) {
        this.nameMain = nameMain;
        this.description = description;
    }

    public TrainingMain() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameMain() {
        return nameMain;
    }

    public void setNameMain(String nameMain) {
        this.nameMain = nameMain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
