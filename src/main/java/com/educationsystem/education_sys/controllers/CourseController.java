package com.educationsystem.education_sys.controllers;

import com.educationsystem.education_sys.Services.CourseService;
import com.educationsystem.education_sys.Services.UserService;
import com.educationsystem.education_sys.dto.CourseDto;
import com.educationsystem.education_sys.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.educationsystem.education_sys.model.Course;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CourseController {
    @Autowired
    private final CourseService courseService;

    private final UserService  userService;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }



    @PostMapping("/courses")
    public ResponseEntity<Object> createCourse(
            @RequestBody CourseDto courseDto,
            @RequestParam("instructorId") String instructorId
    ) {
        try {
            User instructor = userService.getUser(instructorId).orElse(null);
            if (instructor != null) {
                Course createdCourse = courseService.createCourse(courseDto, instructor);
                return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instructor not found with ID: " + instructorId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating course: " + e.getMessage());
        }
    }




    @GetMapping("/courses/{id}")
    public @ResponseBody ResponseEntity<Optional<Course>> getCourseById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseById(id));
    }

    @GetMapping("/courses/{userId}/courses")
    public ResponseEntity<List<Course>> getInstructorCourses(@PathVariable String userId){
        List<Course> courses = courseService.getInstructorCourses(userId);
        return ResponseEntity.ok(courses);
    }

}
