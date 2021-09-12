package com.inqoo.trainingservice.app.order;

import com.inqoo.trainingservice.app.absence.AbsenceType;
import com.inqoo.trainingservice.app.trainer.Trainer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
class AbsenceProjection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Trainer trainer;
    private LocalDate startAbsence;
    private LocalDate endDate;

    public AbsenceProjection(Trainer trainer, LocalDate startAbsence, LocalDate endDate) {
        this.trainer = trainer;
        this.startAbsence = startAbsence;
        this.endDate = endDate;
    }

    public AbsenceProjection() {
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public LocalDate getStartAbsence() {
        return startAbsence;
    }

    public void setStartAbsence(LocalDate startAbsence) {
        this.startAbsence = startAbsence;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}
