package com.inqoo.trainingservice.app.course;

import com.inqoo.trainingservice.app.subcategory.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByName(String name);
    
    @Query("select name from Course where name in :names")
    List<String> getAllCourseName(List<String> names);

    List<Course> findByNameIn(List<String> names);
}
