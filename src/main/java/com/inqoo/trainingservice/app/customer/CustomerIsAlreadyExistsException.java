package com.inqoo.trainingservice.app.customer;

public class CustomerIsAlreadyExistsException extends RuntimeException{
    public CustomerIsAlreadyExistsException() {
        super("Customer is already exist");
    }
}
