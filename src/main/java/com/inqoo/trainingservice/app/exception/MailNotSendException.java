package com.inqoo.trainingservice.app.exception;

public class MailNotSendException extends RuntimeException {
    public MailNotSendException(String message) {
        super(message);
    }
}
