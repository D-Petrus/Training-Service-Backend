package com.inqoo.trainingservice.app.exception;

public class NameAlreadyTakenException extends RuntimeException {

    public NameAlreadyTakenException(String message) {
        super(message);
    }
}
