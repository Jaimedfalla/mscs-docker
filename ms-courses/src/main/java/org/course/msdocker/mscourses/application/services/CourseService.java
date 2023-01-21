package org.course.msdocker.mscourses.application.services;

import org.course.msdocker.mscourses.application.handlers.AgregadorHandler;
import org.course.msdocker.mscourses.application.handlers.EliminadorHandler;
import org.course.msdocker.mscourses.application.handlers.IHandleable;
import org.course.msdocker.mscourses.infraestructure.clientshttp.UserClientRest;
import org.course.msdocker.mscourses.infraestructure.dtos.User;
import org.course.msdocker.mscourses.infraestructure.entities.Course;
import org.course.msdocker.mscourses.infraestructure.entities.CourseUser;
import org.course.msdocker.mscourses.infraestructure.repositories.ICourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CourseService implements  ICourseService{

    @Autowired
    private ICourseRepository _repository;

    @Autowired
    private UserClientRest _userClient;

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

    @Override
    @Transactional
    public Optional<User> assignUser(User user, Long idCourse) {
        return this.manageUser(user,idCourse,
                u -> _userClient.getDetail(u.getId()),
                new AgregadorHandler());
    }

    @Override
    @Transactional
    public Optional<User> createUser(User user, Long idCourse) {
        return this.manageUser(user,idCourse,
                u -> _userClient.createUser(u),
                new AgregadorHandler());
    }

    @Override
    @Transactional
    public Optional<User> unassignUser(User user, Long idCourse) {
        return this.manageUser(user,idCourse,
                u -> _userClient.createUser(u),
                new EliminadorHandler());
    }

    private Optional<User> manageUser(User user, Long idCourse, Function<User,User> handle, IHandleable handler){
        Optional<Course> courseOpt = _repository.findById(idCourse);

        if(!courseOpt.isPresent()) return Optional.empty();

        User userMs = handle.apply(user);
        Course course = courseOpt.get();
        CourseUser courseUser = new CourseUser();
        courseUser.setUserId(userMs.getId());
        handler.handler(course,courseUser);
        _repository.save(course);

        return  Optional.of(userMs);
    }
}
