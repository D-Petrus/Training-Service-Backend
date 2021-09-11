package com.inqoo.trainingservice.app.absence;

import com.inqoo.trainingservice.app.trainer.Trainer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @ManyToOne
    private Trainer trainer;
    private LocalDate startVacation;
    private LocalDate endVacation;
    private AbsenceType vacationType;

    public Absence(Trainer trainer, LocalDate startVacation, LocalDate endVacation, AbsenceType vacationType) {
        this.trainer = trainer;
        this.startVacation = startVacation;
        this.endVacation = endVacation;
        this.vacationType = vacationType;
    }

    public Absence() {
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public LocalDate getStartVacation() {
        return startVacation;
    }

    public void setStartVacation(LocalDate startVacation) {
        this.startVacation = startVacation;
    }

    public LocalDate getEndVacation() {
        return endVacation;
    }

    public void setEndVacation(LocalDate endVacation) {
        this.endVacation = endVacation;
    }

    public AbsenceType getVacationType() {
        return vacationType;
    }

    public void setVacationType(AbsenceType vacationType) {
        this.vacationType = vacationType;
    }
}
