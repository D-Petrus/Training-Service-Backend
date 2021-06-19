package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.exception.TooLongDescriptionException;
import com.inqoo.trainingservice.app.models.Course;
import com.inqoo.trainingservice.app.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course saveNewCourse(Course course) {
        validateInputs(course, course.getName());
        return courseRepository.save(course);
    }

    private void validateInputs(Course course, String name) {
        if (!validateCharacters(course.getDescription())) {
            throw new TooLongDescriptionException("Too long description");
        }
        if (courseRepository.findByName(name).isPresent()) {
            throw new NameAlreadyTakenException("name already taken");
        }

    }

    public List<Course> getAllCoursesList() {
        return courseRepository.findAll();
    }

    Optional<Course> findByName(String name) {
        return courseRepository.findByName(name);
    }

    private boolean validateCharacters(String description) {
        int limitDescription = 201;
        return description.length() < limitDescription;
    }
}
