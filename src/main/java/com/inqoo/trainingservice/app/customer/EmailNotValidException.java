package com.inqoo.trainingservice.app.customer;

public class EmailNotValidException extends RuntimeException {
    public EmailNotValidException() {
        super("Email is not valid");
    }
}
