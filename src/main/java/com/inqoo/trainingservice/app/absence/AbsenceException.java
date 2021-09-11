package com.inqoo.trainingservice.app.absence;


public class AbsenceException extends RuntimeException {
    public AbsenceException() {
        super("Trainer not available");
    }

}
