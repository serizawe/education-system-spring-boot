package com.educationsystem.education_sys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="enrollments")
@Data
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @OneToOne
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    User student;


    @OneToOne
    @JoinColumn(name = "course_id",referencedColumnName = "id")
    Course course;

}
