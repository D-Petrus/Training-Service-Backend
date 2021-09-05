package com.inqoo.trainingservice.app.unavailability;

import com.inqoo.trainingservice.app.trainer.Trainer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Unavailability {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    private Trainer trainer;
    private LocalDate dayOfAbsence;

    public Unavailability(Trainer trainer, LocalDate dayOfAbsence) {
        this.trainer = trainer;
        this.dayOfAbsence = dayOfAbsence;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public LocalDate getDayOfAbsence() {
        return dayOfAbsence;
    }

    public void setDayOfAbsence(LocalDate dayOfAbsence) {
        this.dayOfAbsence = dayOfAbsence;
    }
}
