package org.course.mscsdocker.mscsdocker.repositories;

import org.course.mscsdocker.mscsdocker.models.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioRepository extends CrudRepository<User,Long> {
}
