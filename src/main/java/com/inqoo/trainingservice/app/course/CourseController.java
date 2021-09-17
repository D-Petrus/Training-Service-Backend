package com.inqoo.trainingservice.app.course;

import com.inqoo.trainingservice.app.subcategory.Subcategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
class CourseController {

    private CourseService courseService;

    private CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/{subcategoryName}")
    private Course saveNew(@RequestBody Course course, @PathVariable String subcategoryName) {
        log.info("Saving new course: "+course.getName());
       return courseService.saveNewCourse(subcategoryName, course);
    }

    @GetMapping
    private List<Course> getList() {
        log.info("Getting list of courses");
        return courseService.getAllCoursesList();
    }

    @GetMapping(value = "/names")
    private List<String> findByNameIn(@RequestBody CourseNamesList courseNames) {
        log.info("Getting course by name");
        return courseService.getAllCourseName(courseNames.getCourseNames());
    }

}
