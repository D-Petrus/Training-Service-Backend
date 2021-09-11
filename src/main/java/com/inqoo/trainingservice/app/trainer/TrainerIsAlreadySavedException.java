package com.inqoo.trainingservice.app.trainer;

 class TrainerIsAlreadySavedException extends RuntimeException{
    public TrainerIsAlreadySavedException() {super("Trainer is already saved");
    }
}
