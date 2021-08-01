package com.inqoo.trainingservice.app.customer;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() {
        super("Customer not found");
    }
}
