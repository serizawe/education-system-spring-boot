package com.educationsystem.education_sys.repositories;

import com.educationsystem.education_sys.model.Course;
import com.educationsystem.education_sys.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {

    @Query("SELECT e.course FROM Enrollment e WHERE e.student.userId = :studentId")
    List<Course> findCoursesByStudentUserId(String studentId);

    boolean findByStudentUserIdAndCourseCourseId(String userId, String courseId);

    boolean existsByCourseCourseIdAndStudentUserId(String courseId, String studentId);
}
