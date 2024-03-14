package com.educationsystem.education_sys.repositories;

import com.educationsystem.education_sys.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
}
