package org.course.msdocker.mscourses.application.handlers;

import org.course.msdocker.mscourses.infraestructure.entities.Course;
import org.course.msdocker.mscourses.infraestructure.entities.CourseUser;

public class AgregadorHandler implements  IHandleable{

    @Override
    public void handler(Course course, CourseUser courseUser) {
        course.addCourseUser(courseUser);
    }
}
