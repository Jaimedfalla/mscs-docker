package org.course.msdocker.mscourses.services;

import org.course.msdocker.mscourses.entities.Course;
import org.course.msdocker.mscourses.repositories.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements  ICourseService{

    @Autowired
    private ICourseRepository _repository;

    @Override
    @Transactional(readOnly = true)
    public List<Course> getAll() {
        return (List<Course>) _repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> findById(Long id) {
        return _repository.findById(id);
    }

    @Override
    @Transactional
    public Course save(Course course) {
        return _repository.save(course);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        _repository.deleteById(id);
    }
}
