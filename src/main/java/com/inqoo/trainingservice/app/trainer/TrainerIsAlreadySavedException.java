package com.inqoo.trainingservice.app.trainer;

public class TrainerIsAlreadySavedException extends RuntimeException{
    public TrainerIsAlreadySavedException() {super("Trainer is already saved");
    }
}
