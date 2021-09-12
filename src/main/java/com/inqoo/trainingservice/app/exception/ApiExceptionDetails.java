package com.inqoo.trainingservice.app.exception;

import java.time.LocalDateTime;

class ApiExceptionDetails {
    private LocalDateTime timestamp;
    private final String message;

    public ApiExceptionDetails(LocalDateTime timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
