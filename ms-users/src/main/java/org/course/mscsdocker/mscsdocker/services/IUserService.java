package org.course.mscsdocker.mscsdocker.services;

import org.course.mscsdocker.mscsdocker.models.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void delete(Long id);
}
