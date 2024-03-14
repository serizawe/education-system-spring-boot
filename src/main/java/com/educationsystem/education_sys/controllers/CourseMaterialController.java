package com.educationsystem.education_sys.controllers;

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
    @Autowired
    private final CourseMaterialService courseMaterialService;

    @GetMapping("/{courseId}/materials")
    public ResponseEntity<List<CourseMaterial>> getCourseMaterials(@PathVariable Long courseId){
        List<CourseMaterial> courseMaterials = courseMaterialService.getCourseMaterials(courseId);
        return ResponseEntity.status(HttpStatus.OK).body(courseMaterials);
    }
    @GetMapping("/{courseId}/materials/{materialId}")
    public ResponseEntity<CourseMaterial> getCourseMaterialById(@PathVariable Long courseId,@PathVariable Long materialId){
        CourseMaterial courseMaterial = courseMaterialService.getCourseMaterial(materialId);
        return ResponseEntity.status(HttpStatus.OK).body(courseMaterial);
    }



}
