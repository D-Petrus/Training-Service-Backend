package com.inqoo.trainingservice.app.customer;

public class HomeNumberNotValidException extends RuntimeException {
    public HomeNumberNotValidException(String message) {
        super(message);
    }
}
