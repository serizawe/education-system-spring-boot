package com.educationsystem.education_sys.controllers;

import com.educationsystem.education_sys.Services.AuthService;
import com.educationsystem.education_sys.Services.CourseMaterialService;
import com.educationsystem.education_sys.model.Course;
import com.educationsystem.education_sys.model.CourseMaterial;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{courseId}/materials")
    public ResponseEntity<List<CourseMaterial>> getCourseMaterials(@PathVariable Long courseId){
        List<CourseMaterial> courseMaterials = courseMaterialService.getCourseMaterials(courseId);
        return ResponseEntity.ok(courseMaterials);
    }
    @GetMapping("/{courseId}/materials/{materialId}")
    public ResponseEntity<CourseMaterial> getCourseMaterialById(@PathVariable Long courseId,@PathVariable Long materialId){
        CourseMaterial courseMaterial = courseMaterialService.getCourseMaterial(materialId);
        return ResponseEntity.ok(courseMaterial);
    }

    @DeleteMapping("/{courseId}/materials/{materialId}")
    public ResponseEntity<String> deleteCourseMaterialById(@PathVariable Long courseId, @PathVariable Long materialId) {
        try {
            courseMaterialService.deleteCourseMaterialById(materialId);
            return ResponseEntity.ok("Course material deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting course material: " + e.getMessage());
        }
    }
    @PutMapping("/{courseId}/materials/{materialId}")
    public ResponseEntity<String> updateCourseMaterialById(@PathVariable Long courseId, @PathVariable Long materialId, @RequestBody CourseMaterial updatedMaterial) {
        try {
            CourseMaterial result = courseMaterialService.updateCourseMaterialById(materialId, updatedMaterial);
            return ResponseEntity.ok("Course material updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating course material: " + e.getMessage());
        }
    }






}
