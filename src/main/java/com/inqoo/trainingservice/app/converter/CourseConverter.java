package com.inqoo.trainingservice.app.converter;

import com.inqoo.trainingservice.app.DTO.CourseDTO;
import com.inqoo.trainingservice.app.models.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter {
    public CourseDTO entityToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO(
                course.getName(),
                course.getDescription(),
                course.getDuration(),
                course.getPrice(),
                course.getUuidCourse()
        );

        return courseDTO;
    }

    public Course dtoToEntity(CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setDuration(courseDTO.getDuration());
        course.setPrice(courseDTO.getPrice());
        course.setUuidCourse(courseDTO.getUuidCourse());
        return course;
    }
}
