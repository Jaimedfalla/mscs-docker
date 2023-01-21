package org.course.msdocker.mscourses.infraestructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="courses_users")
public class CourseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="userId",unique = true)
    private Long userId;

    @Override
    public boolean equals(Object obj){
        if(this==obj)return true;

        if(!(obj instanceof  CourseUser)) return false;

        CourseUser o = (CourseUser) obj;
        return this.userId != null && this.userId == o.userId;
    }
}
