package com.inqoo.trainingservice.app.customer;

public class HomeNumberNotValidException extends RuntimeException {
    public HomeNumberNotValidException(){
        super("Home number is not valid");
    }
}
