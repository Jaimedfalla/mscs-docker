package org.course.msdocker.mscourses.controllers;

import feign.FeignException;
import jakarta.validation.Valid;
import org.course.msdocker.mscourses.application.helpers.Validator;
import org.course.msdocker.mscourses.infraestructure.dtos.User;
import org.course.msdocker.mscourses.infraestructure.entities.Course;
import org.course.msdocker.mscourses.application.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    private ICourseService _service;

    @GetMapping
    public List<Course> all(){
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id){
        Optional<Course> userOpt = this._service.findByIdUsers(id);
        if(userOpt.isPresent()){
            return ResponseEntity.ok(userOpt.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Course course, BindingResult result){
        if(result.hasErrors()) return Validator.getResultValidation(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(_service.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Course user,BindingResult result,@PathVariable Long id){
        if(result.hasErrors()) return Validator.getResultValidation(result);

        Optional<Course> userOpt = _service.findById(id);
        if(userOpt.isPresent()){
            Course userDb = userOpt.get();
            userDb.setName(user.getName());

            return ResponseEntity.status(HttpStatus.CREATED).body(_service.save(userDb));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Course> user = _service.findById(id);

        if(user.isPresent()){
            _service.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/assign/{courseId}")
    public ResponseEntity<?> assingUser(@RequestBody User user,@PathVariable Long courseId){
       Optional<User> userOpt;
       try{
           userOpt = _service.assignUser(user,courseId);
           if(!userOpt.isPresent()) return ResponseEntity.notFound().build();

           return ResponseEntity.status(HttpStatus.CREATED).body(userOpt.get());
       }catch (FeignException e){
           return ResponseEntity.internalServerError().body(Collections.singletonMap("error","Error in comunication channel"+e.getMessage()));
       }
    }

    @PostMapping("/create/{courseId}")
    public ResponseEntity<?> createUser(@RequestBody User user,@PathVariable Long courseId){
        Optional<User> userOpt;
        try{
            userOpt = _service.createUser(user,courseId);
            if(!userOpt.isPresent()) return ResponseEntity.notFound().build();

            return ResponseEntity.status(HttpStatus.CREATED).body(userOpt.get());
        }catch (FeignException e){
            return ResponseEntity.internalServerError().body(Collections.singletonMap("error","Error in comunication channel"+e.getMessage()));
        }
    }

    @DeleteMapping("/unassign/{courseId}")
    public ResponseEntity<?> deleteUser(@RequestBody User user,@PathVariable Long courseId){
        Optional<User> userOpt;
        try{
            userOpt = _service.unassignUser(user,courseId);
            if(!userOpt.isPresent()) return ResponseEntity.notFound().build();

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userOpt.get());
        }catch (FeignException e){
            return ResponseEntity.internalServerError().body(Collections.singletonMap("error","Error in comunication channel"+e.getMessage()));
        }
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        _service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}