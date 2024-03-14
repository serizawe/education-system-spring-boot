package com.educationsystem.education_sys.Services;

import com.educationsystem.education_sys.model.Course;
import com.educationsystem.education_sys.model.CourseMaterial;
import com.educationsystem.education_sys.repositories.CourseMaterialRepository;
import com.educationsystem.education_sys.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
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
}
