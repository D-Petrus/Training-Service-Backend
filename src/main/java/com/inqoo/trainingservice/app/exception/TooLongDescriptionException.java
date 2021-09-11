package com.inqoo.trainingservice.app.exception;

public class TooLongDescriptionException extends RuntimeException {

    public TooLongDescriptionException() {
        super("Too long description");
    }
}
