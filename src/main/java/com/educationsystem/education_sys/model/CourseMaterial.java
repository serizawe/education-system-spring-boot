package com.educationsystem.education_sys.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="courseMaterials")
@Data
public class CourseMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private String materialId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id",referencedColumnName = "courseId")
    private Course course;

    private String name;
    private String description;
    private String videoUrl;
    private String thumbnailUrl;

}
