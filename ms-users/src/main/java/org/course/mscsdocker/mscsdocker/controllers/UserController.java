package org.course.mscsdocker.mscsdocker.controllers;

import jakarta.validation.Valid;
import org.course.mscsdocker.mscsdocker.application.helpers.Validator;
import org.course.mscsdocker.mscsdocker.models.entities.User;
import org.course.mscsdocker.mscsdocker.application.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ResponseEntity<?> save(@Valid @RequestBody User user, BindingResult result){
        if(result.hasErrors()) return Validator.getResultValidation(result);

        if(_service.findByEmail(user.getEmail()))
            return ResponseEntity.badRequest().body(Collections.singletonMap("error","User already exists"));

        return ResponseEntity.status(HttpStatus.CREATED).body(_service.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User user,BindingResult result, @PathVariable Long id){
        if(result.hasErrors()) return Validator.getResultValidation(result);

        Optional<User> userOpt = _service.findById(id);
        if(userOpt.isPresent()){
            User userDb = userOpt.get();

            if(!user.getEmail().equalsIgnoreCase(userDb.getEmail()) &&   _service.findByEmail(user.getEmail()))
                return ResponseEntity.badRequest().body(Collections.singletonMap("error","User already exists"));

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
