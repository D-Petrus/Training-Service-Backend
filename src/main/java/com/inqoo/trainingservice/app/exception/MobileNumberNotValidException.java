package com.inqoo.trainingservice.app.exception;

public class MobileNumberNotValidException extends RuntimeException {
    public MobileNumberNotValidException() {
        super("Mobile number is not valid");
    }
}
