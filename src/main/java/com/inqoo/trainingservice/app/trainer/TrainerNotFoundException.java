package com.inqoo.trainingservice.app.trainer;

public class TrainerNotFoundException extends RuntimeException {
    public TrainerNotFoundException() {
        super("Trainer not available");
    }
}
