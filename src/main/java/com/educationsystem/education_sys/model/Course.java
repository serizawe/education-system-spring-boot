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
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(nullable = false, updatable = false)
  private String courseId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "instructor_id",referencedColumnName = "userId")
  private User instructor;

  private String name;
  private String description;
  private String thumbnailUrl;

  @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<CourseMaterial> courseMaterials;

  @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<Enrollment> enrollments;

}
