package com.educationsystem.education_sys.Services;

import com.educationsystem.education_sys.model.Course;
import com.educationsystem.education_sys.model.CourseMaterial;
import com.educationsystem.education_sys.repositories.CourseMaterialRepository;
import com.educationsystem.education_sys.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseMaterialService {
    private final CourseMaterialRepository courseMaterialRepository;
    private final CourseRepository courseRepository;
    public List<CourseMaterial> getCourseMaterials(Long courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if(optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            return course.getCourseMaterials();
        }
        return null;
    }

    public CourseMaterial getCourseMaterial(Long materialId) {
        return courseMaterialRepository.findById(materialId).orElseThrow(IllegalArgumentException::new);
    }

    @PreAuthorize("@courseMaterialService.isOwner(#materialId, authentication)")
    public void deleteCourseMaterialById(Long materialId) {
        try {
            courseMaterialRepository.deleteById(materialId);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting course material with ID: " + materialId, e);
        }
    }

    @PreAuthorize("@courseMaterialService.isOwner(#materialId, authentication)")
    public CourseMaterial updateCourseMaterialById(Long materialId, CourseMaterial updatedMaterial) {
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

    public boolean isOwner(Long materialId, Authentication authentication) {
        String email = authentication.getName();
        CourseMaterial courseMaterial = courseMaterialRepository.findById(materialId).orElse(null);
        return courseMaterial != null && courseMaterial.getCourse().getInstructor().getEmail().equals(email);
    }


}
