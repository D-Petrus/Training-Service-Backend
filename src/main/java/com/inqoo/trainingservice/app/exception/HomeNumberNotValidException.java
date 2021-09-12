package com.inqoo.trainingservice.app.exception;

public class HomeNumberNotValidException extends RuntimeException {
    public HomeNumberNotValidException(){
        super("Home number is not valid");
    }
}
