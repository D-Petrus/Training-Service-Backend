package com.inqoo.trainingservice.app.order;

public class OrderForTrainerNotCreatedException extends RuntimeException{
    public OrderForTrainerNotCreatedException() {super("Could not create an order for trainer");
    }
}
