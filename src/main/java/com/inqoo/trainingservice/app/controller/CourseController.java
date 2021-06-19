package com.inqoo.trainingservice.app.controller;

import com.inqoo.trainingservice.app.models.Course;
import com.inqoo.trainingservice.app.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainings")
public class CourseController {

    private CourseService courseService;

    private CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    private List<Course> getList() {
        return courseService.getAllCoursesList();
    }

    @PostMapping
    private Course saveNew(@RequestBody Course course) {
       return courseService.saveNewCourse(course);
    }
}
