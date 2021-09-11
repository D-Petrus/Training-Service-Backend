package com.inqoo.trainingservice.app.exception;

public class TrainerNotFoundException extends RuntimeException {
    public TrainerNotFoundException() {
        super("Trainer not available");
    }
}
