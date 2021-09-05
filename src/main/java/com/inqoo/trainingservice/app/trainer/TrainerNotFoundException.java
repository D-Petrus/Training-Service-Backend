package com.inqoo.trainingservice.app.trainer;

import com.inqoo.trainingservice.app.unavailability.Unavailability;

public class TrainerNotFoundException extends RuntimeException {
    public TrainerNotFoundException() {
        super("Trainer not available");
    }
}
