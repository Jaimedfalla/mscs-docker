package org.course.msdocker.mscourses.repositories;

import org.course.msdocker.mscourses.entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface ICourseRepository extends CrudRepository<Course, Long> {
}
