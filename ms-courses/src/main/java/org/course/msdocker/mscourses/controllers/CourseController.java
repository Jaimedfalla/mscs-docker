package org.course.msdocker.mscourses.controllers;

import org.course.msdocker.mscourses.entities.Course;
import org.course.msdocker.mscourses.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Optional<Course> userOpt = this._service.findById(id);
        if(userOpt.isPresent()){
            return ResponseEntity.ok(userOpt.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Course> save(@RequestBody Course course){
        return ResponseEntity.status(HttpStatus.CREATED).body(_service.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@RequestBody Course user,@PathVariable Long id){
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
}
}
