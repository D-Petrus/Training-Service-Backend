package com.inqoo.trainingservice.app.exception;

public class TrainerIsAlreadySavedException extends RuntimeException{
    public TrainerIsAlreadySavedException() {super("Trainer is already saved");
    }
}
