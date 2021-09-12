package com.inqoo.trainingservice.app.exception;

public class EmailNotValidException extends RuntimeException {
    public EmailNotValidException() {
        super("Email is not valid");
    }
}
