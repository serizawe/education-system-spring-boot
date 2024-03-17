package com.educationsystem.education_sys.controllers;

import com.educationsystem.education_sys.Services.CourseMaterialService;
import com.educationsystem.education_sys.Services.CourseService;
import com.educationsystem.education_sys.dto.CourseMaterialDto;
import com.educationsystem.education_sys.model.Course;
import com.educationsystem.education_sys.model.CourseMaterial;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/courses")
public class CourseMaterialController {
    private final CourseMaterialService courseMaterialService;
    private final CourseService courseService;

    public ResponseEntity<List<CourseMaterial>> getCourseMaterials(@PathVariable String courseId){
        Optional<Course> optionalCourse = courseService.getCourseById(courseId);
        if(optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            return ResponseEntity.ok(course.getCourseMaterials());
        }
        return ResponseEntity.notFound().build(); // Handle course not found
    }

    @GetMapping("/{courseId}/materials/{materialId}")
    public ResponseEntity<CourseMaterial> getCourseMaterialById(@PathVariable String courseId,@PathVariable String materialId){
        CourseMaterial courseMaterial = courseMaterialService.getCourseMaterial(materialId);
        return ResponseEntity.ok(courseMaterial);
    }

    @DeleteMapping("/{courseId}/materials/{materialId}")
    public ResponseEntity<String> deleteCourseMaterialById(@PathVariable String courseId, @PathVariable String materialId) {
        try {
            courseMaterialService.deleteCourseMaterialById(materialId);
            return ResponseEntity.ok("Course material deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting course material: " + e.getMessage());
        }
    }
    @PutMapping("/{courseId}/materials/{materialId}")
    public ResponseEntity<String> updateCourseMaterialById(@PathVariable String courseId, @PathVariable String materialId, @RequestBody CourseMaterial updatedMaterial) {
        try {
            CourseMaterial result = courseMaterialService.updateCourseMaterialById(materialId, updatedMaterial);
            return ResponseEntity.ok("Course material updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating course material: " + e.getMessage());
        }
    }
    @PostMapping("/{courseId}/materials")
    public ResponseEntity<CourseMaterial> createCourseMaterial(
            @PathVariable String courseId,
            @RequestBody CourseMaterialDto courseMaterialDto) {
        try {
            CourseMaterial createdMaterial = courseMaterialService.createCourseMaterial(courseId, courseMaterialDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMaterial);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    }


