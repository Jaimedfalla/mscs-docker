package org.course.msdocker.mscourses.infraestructure.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.course.msdocker.mscourses.infraestructure.dtos.User;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long Id;

    @NotEmpty
    @Column(name = "name")
    private String Name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name="course_id")
    private List<CourseUser> coursesUsers;

    @Transient //Indica que este atributo no es persistente
    private List<User> users;

    public Course(){
        coursesUsers = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addCourseUser(CourseUser courseUser){
        coursesUsers.add(courseUser);
    }

    public void deleteCourseUser(CourseUser courseUser){
        coursesUsers.remove(courseUser);
    }
}
