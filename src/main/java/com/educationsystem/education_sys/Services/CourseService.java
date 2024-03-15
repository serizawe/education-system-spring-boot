package com.educationsystem.education_sys.Services;

import com.educationsystem.education_sys.model.Course;
import com.educationsystem.education_sys.model.User;
import com.educationsystem.education_sys.repositories.CourseRepository;
import com.educationsystem.education_sys.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public List<Course> getInstructorCourses(String username) {
        User instructor = userRepository.findByUsername(username);
        if(instructor == null)
        {
            throw new IllegalArgumentException("Instructor not found with username: " + username);
        }
        return instructor.getCourses();
    }
}
