package com.inqoo.trainingservice.app.service;

import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.exception.SubcategoryNotFoundException;
import com.inqoo.trainingservice.app.exception.TooLongDescriptionException;
import com.inqoo.trainingservice.app.models.Course;
import com.inqoo.trainingservice.app.models.Subcategory;
import com.inqoo.trainingservice.app.repository.CourseRepository;
import com.inqoo.trainingservice.app.repository.SubcategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    private SubcategoryRepository subcategoryRepository;

    public CourseService(CourseRepository courseRepository, SubcategoryRepository subcategoryRepository) {
        this.courseRepository = courseRepository;
        this.subcategoryRepository = subcategoryRepository;
    }

    public Course saveNewCourse(String subcategoryName, Course course) {
        Optional<Subcategory> byName = subcategoryRepository.findByName(subcategoryName);
        if (byName.isPresent()) {
            validateInputs(course, course.getName());
            Subcategory subcategory = byName.get();
            subcategory.addCourse(course);
            subcategoryRepository.save(subcategory);
            return courseRepository.findByName(course.getName()).get();
        } else {
            throw new SubcategoryNotFoundException("Subcategory not found");
        }
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
