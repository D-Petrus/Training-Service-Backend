package com.inqoo.trainingservice.app.customer;

public class CustomerIsAlreadyExistsException extends RuntimeException{
    public CustomerIsAlreadyExistsException(String message) {
        super(message);
    }
}
