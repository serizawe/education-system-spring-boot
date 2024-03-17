package com.educationsystem.education_sys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="enrollments")
@Data
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private String enrollmentId;

    @OneToOne
    @JoinColumn(name = "student_id",referencedColumnName = "userId")
    User student;


    @OneToOne
    @JoinColumn(name = "course_id",referencedColumnName = "courseId")
    Course course;

}
