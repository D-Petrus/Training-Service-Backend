package com.inqoo.trainingservice.app.trainer;

 public class TrainerIsAlreadySavedException extends RuntimeException{
    public TrainerIsAlreadySavedException(String message) {
        super(message);
    }
}
