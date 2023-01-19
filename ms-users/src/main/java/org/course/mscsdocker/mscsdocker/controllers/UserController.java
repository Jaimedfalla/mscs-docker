package org.course.mscsdocker.mscsdocker.controllers;

import org.course.mscsdocker.mscsdocker.models.entities.User;
import org.course.mscsdocker.mscsdocker.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private IUserService _service;

    @GetMapping
    public List<User> all(){
        return _service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> user(@PathVariable Long id){
        Optional<User> userOpt = this._service.findById(id);
        if(userOpt.isPresent()){
            return ResponseEntity.ok(userOpt.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(_service.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user,@PathVariable Long id){
        Optional<User> userOpt = _service.findById(id);
        if(userOpt.isPresent()){
            User userDb = userOpt.get();
            userDb.setName(user.getName());
            userDb.setEmail(user.getEmail());
            userDb.setPassword(user.getPassword());

            return ResponseEntity.status(HttpStatus.CREATED).body(_service.save(userDb));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<User> user = _service.findById(id);

        if(user.isPresent()){
            _service.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
