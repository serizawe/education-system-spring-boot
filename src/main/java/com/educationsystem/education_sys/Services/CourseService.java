package com.educationsystem.education_sys.Services;

import com.educationsystem.education_sys.dto.CourseDto;
import com.educationsystem.education_sys.model.Course;
import com.educationsystem.education_sys.model.User;
import com.educationsystem.education_sys.repositories.CourseRepository;
import com.educationsystem.education_sys.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private  final UserService userService;
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(String id) {
        return courseRepository.findById(id);
    }

    public List<Course> getInstructorCourses(String userId) {
        User instructor = userRepository.findByUserId(userId).orElse(null);
        if(instructor == null)
        {
            throw new IllegalArgumentException("Instructor not found with user id: " + userId);
        }
        return instructor.getCourses();
    }


    @Transactional
    public Course createCourse(CourseDto courseDto, User instructor) {
        String courseId = UUID.randomUUID().toString();;
        Course course = new Course();
        course.setCourseId(courseId);
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setThumbnailUrl(courseDto.getThumbnailUrl());
        course.setInstructor(instructor);
        try {
            return courseRepository.save(course);
        } catch (Exception e) {
            throw new RuntimeException("Error creating course: " + e.getMessage());
        }
    }

}
