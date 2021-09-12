package com.inqoo.trainingservice.app.course;

public class CourseListEmptyException extends RuntimeException {
    public CourseListEmptyException(String message) {
        super(message);
    }
}
