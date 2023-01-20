package org.course.msdocker.mscourses.services;

import java.util.List;
import java.util.Optional;

import org.course.msdocker.mscourses.entities.Course;

public interface ICourseService {
    List<Course> getAll();
    Optional<Course> findById(Long id);
    Course save(Course course);
    void delete(Long id);
}
