package com.inqoo.trainingservice.app.course;

import java.util.ArrayList;
import java.util.List;

public class CourseNamesList {
    List<String> courseNames = new ArrayList<>();

    public CourseNamesList() {
    }

    public List<String> getCourseNames() {
        return courseNames;
    }

    public void setCourseNames(List<String> courseNames) {
        this.courseNames = courseNames;
    }

    public void add(String name) {
        this.courseNames.add(name);
    }
}
