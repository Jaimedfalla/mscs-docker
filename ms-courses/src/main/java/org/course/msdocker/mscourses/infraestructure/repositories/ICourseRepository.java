package org.course.msdocker.mscourses.infraestructure.repositories;

import org.course.msdocker.mscourses.infraestructure.entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface ICourseRepository extends CrudRepository<Course, Long> {
}
