package com.inqoo.trainingservice.app.unavailability;


public class UnavailabilityException extends RuntimeException {
    public UnavailabilityException() {
        super("Trainer not available");
    }
}
