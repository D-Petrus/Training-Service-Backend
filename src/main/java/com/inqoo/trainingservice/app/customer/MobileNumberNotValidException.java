package com.inqoo.trainingservice.app.customer;

public class MobileNumberNotValidException extends RuntimeException {
    public MobileNumberNotValidException() {
        super("Mobile number is not valid");
    }
}
