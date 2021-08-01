package com.inqoo.trainingservice.app.customer;

public class MobileNumberNotValidException extends RuntimeException {
    public MobileNumberNotValidException(String message) {
        super(message);
    }
}
