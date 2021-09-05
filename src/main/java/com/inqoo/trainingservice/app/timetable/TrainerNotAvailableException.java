package com.inqoo.trainingservice.app.timetable;


public class TrainerNotAvailableException extends RuntimeException {
    public TrainerNotAvailableException() {
        super("Trainer not available");
    }
}
