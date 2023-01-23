package org.course.mscsdocker.mscsdocker.application.services;

import org.course.mscsdocker.mscsdocker.infraestructure.clientsHttp.CourseClientRest;
import org.course.mscsdocker.mscsdocker.infraestructure.entities.models.User;
import org.course.mscsdocker.mscsdocker.infraestructure.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements  IUserService{

    @Autowired
    private IUsuarioRepository _repository;

    @Autowired
    private CourseClientRest _clientHttp;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>)this._repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return this._repository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return this._repository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this._repository.deleteById(id);
        _clientHttp.deleteUser(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findByIds(Iterable<Long> ids) {
        return (List<User>)_repository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean findByEmail(String email) {
        return _repository.existsByEmail(email);
    }


}
