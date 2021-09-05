package com.inqoo.trainingservice.app.course;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*", allowedHeaders = "*")
class CourseController {

    private CourseService courseService;

    private CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/{subcategoryName}")
    private Course saveNew(@RequestBody Course course, @PathVariable String subcategoryName) {
       return courseService.saveNewCourse(subcategoryName, course);
    }

    @GetMapping
    private List<Course> getList() {
        return courseService.getAllCoursesList();
    }

    @GetMapping(value = "/names")
    private List<String> findByNameIn(@RequestBody CourseNamesList courseNames) {
        return courseService.getAllCourseName(courseNames.getCourseNames());
    }
}
