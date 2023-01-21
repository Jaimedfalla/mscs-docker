package org.course.msdocker.mscourses.application.handlers;

import org.course.msdocker.mscourses.infraestructure.entities.Course;
import org.course.msdocker.mscourses.infraestructure.entities.CourseUser;

public interface IHandleable {
    void handler(Course course,CourseUser courseUser);
}
