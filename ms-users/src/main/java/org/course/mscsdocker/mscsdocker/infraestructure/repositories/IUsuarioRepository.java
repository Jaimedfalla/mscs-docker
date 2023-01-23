package org.course.mscsdocker.mscsdocker.infraestructure.repositories;

import org.course.mscsdocker.mscsdocker.infraestructure.entities.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUsuarioRepository extends CrudRepository<User,Long> {
    Optional<User> findByEmail(String email);

    @Query("select u from User u where u.email=?1")
    Optional<User> byEmail(String email);

    boolean existsByEmail(String email);
}
