package com.inqoo.trainingservice.app.trainer;

public class TooLongDescriptionOfExperienceException extends RuntimeException {
    public TooLongDescriptionOfExperienceException() {super("Could not create an order for trainer");
    }
}
