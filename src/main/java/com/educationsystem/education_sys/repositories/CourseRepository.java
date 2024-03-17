package com.educationsystem.education_sys.repositories;

import com.educationsystem.education_sys.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,String> {
}
