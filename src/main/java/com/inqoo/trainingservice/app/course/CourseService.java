package com.inqoo.trainingservice.app.course;

import com.inqoo.trainingservice.app.exception.NameAlreadyTakenException;
import com.inqoo.trainingservice.app.subcategory.Subcategory;
import com.inqoo.trainingservice.app.subcategory.SubcategoryNotFoundException;
import com.inqoo.trainingservice.app.exception.TooLongDescriptionException;
import com.inqoo.trainingservice.app.course.Course;
import com.inqoo.trainingservice.app.course.CourseRepository;
import com.inqoo.trainingservice.app.subcategory.SubcategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
            log.warn("Subcategory not found!");
            throw new SubcategoryNotFoundException("Subcategory not found!");
        }
    }

    private void validateInputs(Course course, String name) {
        if (!validateCharacters(course.getDescription())) {
            log.info("Too long description of course!");
            throw new TooLongDescriptionException("Too long description of course!");
        }
        if (courseRepository.findByName(name).isPresent()) {
            log.info("Course name is already taken!");
            throw new NameAlreadyTakenException("Course name is already taken!");
        }

    }

    public List<Course> getAllCoursesList() {
        return courseRepository.findAll();
    }

    public Optional<Course> findByName(String name) {
        return courseRepository.findByName(name);
    }

    private boolean validateCharacters(String description) {
        int limitDescription = 201;
        return description.length() < limitDescription;
    }
    List<String> getAllCourseName(List<String> courseNames) {
        return courseRepository.getAllCourseName(courseNames);
    }
}
