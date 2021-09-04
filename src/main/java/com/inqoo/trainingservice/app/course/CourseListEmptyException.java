package com.inqoo.trainingservice.app.course;

public class CourseListEmptyException extends RuntimeException {
    public CourseListEmptyException() {
        super("Course list is empty");
    }
}
