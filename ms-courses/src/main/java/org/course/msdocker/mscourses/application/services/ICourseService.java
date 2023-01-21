package org.course.msdocker.mscourses.application.services;

import java.util.List;
import java.util.Optional;

import org.course.msdocker.mscourses.infraestructure.dtos.User;
import org.course.msdocker.mscourses.infraestructure.entities.Course;

import javax.swing.text.html.Option;

public interface ICourseService {
    List<Course> getAll();
    Optional<Course> findById(Long id);
    Course save(Course course);
    void delete(Long id);

    Optional<User> assignUser(User user, Long idCourse);
    Optional<User> createUser(User user,Long idCourse);
    Optional<User> unassignUser(User user,Long idCourse);
}
