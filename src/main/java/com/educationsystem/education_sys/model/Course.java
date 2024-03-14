package com.educationsystem.education_sys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.List;

@Entity
@Table(name="courses")
@Data
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long courseId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "instructor_id",referencedColumnName = "id")
  private User instructor;

  private String name;
  private String description;
  private String thumbnailUrl;

  @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<CourseMaterial> courseMaterials;

  @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<Enrollment> enrollments;

}
