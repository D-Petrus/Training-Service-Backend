package com.inqoo.trainingservice.app.job;

import com.inqoo.trainingservice.app.offer.Offer;
import com.inqoo.trainingservice.app.trainer.Trainer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Offer offer;
    @ManyToOne
    private Trainer trainer;
    private LocalDate startCourse;
    private LocalDate endCourse;

    public Job(Offer offer, Trainer trainer, LocalDate startCourse, LocalDate endCourse) {
        this.offer = offer;
        this.trainer = trainer;
        this.startCourse = startCourse;
        this.endCourse = endCourse;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public LocalDate getStartCourse() {
        return startCourse;
    }

    public void setStartCourse(LocalDate startCourse) {
        this.startCourse = startCourse;
    }

    public LocalDate getEndCourse() {
        return endCourse;
    }

    public void setEndCourse(LocalDate endCourse) {
        this.endCourse = endCourse;
    }
}
