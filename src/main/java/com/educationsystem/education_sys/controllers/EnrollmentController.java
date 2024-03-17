package com.educationsystem.education_sys.controllers;

import com.educationsystem.education_sys.Services.EnrollmentService;
import com.educationsystem.education_sys.dto.EnrollmentDto;
import com.educationsystem.education_sys.model.Enrollment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.educationsystem.education_sys.model.Course;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    @GetMapping("/enrolled/{studentId}")
    public ResponseEntity<List<Course>> getStudentCourses(String studentId){
        List<Course> enrolledCourses = enrollmentService.getStudentCoursesByStudentId(studentId);
        return ResponseEntity.ok(enrolledCourses);
    }

    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<String> enrollStudent(@PathVariable String courseId, @RequestBody String studentId) {
        try {
            enrollmentService.enrollStudent(courseId, studentId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Student enrolled successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/{courseId}/enrollment-status/{studentId}")
    public ResponseEntity<Boolean> getEnrollmentStatus(
            @PathVariable String courseId, @PathVariable String studentId) {
        boolean isEnrolled = enrollmentService.isStudentEnrolled(courseId, studentId);
        return ResponseEntity.ok(isEnrolled);
    }
}
