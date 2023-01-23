package org.course.mscsdocker.mscsdocker.application.services;

import org.course.mscsdocker.mscsdocker.infraestructure.entities.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void delete(Long id);
    List<User> findByIds(Iterable<Long> ids);

    boolean findByEmail(String email);
}
