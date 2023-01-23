package org.course.msdocker.mscourses.infraestructure.clientshttp;

import org.course.msdocker.mscourses.infraestructure.dtos.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-usuarios",url = "${SRV_USERS}:${USERS_PORT}/api/v1/users")
public interface UserClientRest {

    @GetMapping("/{id}")
    User getDetail(@PathVariable Long id);

    @PostMapping
    User createUser(@RequestBody User user);

    @GetMapping("/users-courses")
    List<User> findAllByIds(@RequestParam Iterable<Long> ids);
}
