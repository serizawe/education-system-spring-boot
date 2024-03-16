package com.educationsystem.education_sys.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private String role;

    @OneToMany(mappedBy = "instructor",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Course> courses;
}
