package com.educationsystem.education_sys.Services;

import com.educationsystem.education_sys.dto.CourseMaterialDto;
import com.educationsystem.education_sys.model.Course;
import com.educationsystem.education_sys.model.CourseMaterial;
import com.educationsystem.education_sys.repositories.CourseMaterialRepository;
import com.educationsystem.education_sys.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseMaterialService {
    private final CourseMaterialRepository courseMaterialRepository;
    private final CourseRepository courseRepository;
    public List<CourseMaterial> getCourseMaterials(String courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if(optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            return course.getCourseMaterials();
        }
        return null;
    }

    public CourseMaterial getCourseMaterial(String materialId) {
        return courseMaterialRepository.findById(materialId).orElseThrow(IllegalArgumentException::new);
    }


    public void deleteCourseMaterialById(String materialId) {
        try {
            courseMaterialRepository.deleteById(materialId);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting course material with ID: " + materialId, e);
        }
    }


    public CourseMaterial updateCourseMaterialById(String materialId, CourseMaterial updatedMaterial) {
        try {
            CourseMaterial existingMaterial = courseMaterialRepository.findById(materialId).orElse(null);
            if (existingMaterial == null) {
                throw new IllegalArgumentException("Course material with ID " + materialId + " not found");
            }
            existingMaterial.setName(updatedMaterial.getName());
            existingMaterial.setDescription(updatedMaterial.getDescription());
            existingMaterial.setVideoUrl(updatedMaterial.getVideoUrl());
            existingMaterial.setThumbnailUrl(updatedMaterial.getThumbnailUrl());
            return courseMaterialRepository.save(existingMaterial);
        } catch (Exception e) {
            throw new RuntimeException("Error updating course material with ID: " + materialId, e);
        }
    }

    public boolean isOwner(String materialId, Authentication authentication) {
        String email = authentication.getName();
        CourseMaterial courseMaterial = courseMaterialRepository.findById(materialId).orElse(null);
        return courseMaterial != null && courseMaterial.getCourse().getInstructor().getEmail().equals(email);
    }

    @Transactional
    public CourseMaterial createCourseMaterial(String courseId, CourseMaterialDto courseMaterialDto) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with Id: " + courseId));
        CourseMaterial courseMaterial = new CourseMaterial();
        courseMaterial.setName(courseMaterialDto.getName());
        courseMaterial.setDescription(courseMaterialDto.getDescription());
        courseMaterial.setVideoUrl(courseMaterialDto.getVideoUrl());
        courseMaterial.setThumbnailUrl(courseMaterialDto.getThumbnailUrl());
        String materialId = UUID.randomUUID().toString();
        courseMaterial.setMaterialId(materialId);
        courseMaterial.setCourse(course);
        try {
            return courseMaterialRepository.save(courseMaterial);
        } catch (Exception e) {
            throw new RuntimeException("Error creating course material: " + e.getMessage());
        }
    }

}
