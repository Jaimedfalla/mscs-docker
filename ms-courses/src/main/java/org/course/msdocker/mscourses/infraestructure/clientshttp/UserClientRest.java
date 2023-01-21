package org.course.msdocker.mscourses.infraestructure.clientshttp;

import org.course.msdocker.mscourses.infraestructure.dtos.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-usuarios",url = "localhost:8082/api/v1/users")
public interface UserClientRest {

    @GetMapping("/{id}")
    User getDetail(@PathVariable Long id);

    @PostMapping
    User createUser(@RequestBody User user);
}
