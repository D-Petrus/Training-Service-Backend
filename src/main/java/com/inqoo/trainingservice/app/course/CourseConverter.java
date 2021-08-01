package com.inqoo.trainingservice.app.course;


public class CourseConverter {
    public CourseDTO entityToDTO(Course course) {
        CourseDTO courseDTO = new CourseDTO(
                course.getName(),
                course.getDescription(),
                course.getDuration(),
                course.getPrice(),
                course.getCourseUUID()
        );

        return courseDTO;
    }

    public Course dtoToEntity(CourseDTO courseDTO) {
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setDuration(courseDTO.getDuration());
        course.setPrice(courseDTO.getPrice());
        course.setCourseUUID(courseDTO.getCourseUUID());
        return course;
    }
}
