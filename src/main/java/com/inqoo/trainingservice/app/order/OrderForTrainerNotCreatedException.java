package com.inqoo.trainingservice.app.order;

public class OrderForTrainerNotCreatedException extends RuntimeException{
    public OrderForTrainerNotCreatedException(String message) {
        super(message);
    }
}
