package org.course.mscsdocker.mscsdocker.infraestructure.clientsHttp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msvc-courses",url = "host.docker.internal:8083/api/v1/courses")
public interface CourseClientRest {

    @DeleteMapping("/delete-user/{id}")
    void deleteUser(@PathVariable Long id);
}
