package com.educationsystem.education_sys.Services;

import com.educationsystem.education_sys.model.Course;
import com.educationsystem.education_sys.model.Enrollment;
import com.educationsystem.education_sys.model.User;
import com.educationsystem.education_sys.repositories.CourseRepository;
import com.educationsystem.education_sys.repositories.EnrollmentRepository;
import com.educationsystem.education_sys.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public List<Course> getStudentCoursesByStudentId(String studentId) {
        return  enrollmentRepository.findCoursesByStudentUserId(studentId);
    }
    @Transactional
    public void enrollStudent(String courseId, String studentId) {
        boolean isEnrolled = enrollmentRepository.findByStudentUserIdAndCourseCourseId(studentId, courseId);
        if (isEnrolled) {
            throw new RuntimeException("Student is already enrolled in the course");
        }
        try {
            String enrollmentId = UUID.randomUUID().toString();
            User student = userRepository.findById(studentId)
                    .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + studentId));
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));
            Enrollment enrollment = new Enrollment();
            enrollment.setEnrollmentId(enrollmentId);
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            enrollmentRepository.save(enrollment);
        } catch (Exception e) {
            throw new RuntimeException("Error enrolling student in the course", e);
        }
    }

    public boolean isStudentEnrolled(String courseId, String studentId) {
        return enrollmentRepository.existsByCourseCourseIdAndStudentUserId(courseId, studentId);
    }
}
