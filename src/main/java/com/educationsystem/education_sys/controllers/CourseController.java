package com.educationsystem.education_sys.controllers;

import com.educationsystem.education_sys.Services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }

    @GetMapping("/courses/{id}")
    public @ResponseBody ResponseEntity<Optional<Course>> getCourseById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseById(id));
    }

    @GetMapping("/{username}/courses")
    public ResponseEntity<List<Course>> getInstructorCourses(@PathVariable String username){
        List<Course> courses = courseService.getInstructorCourses(username);
        return ResponseEntity.ok(courses);
    }

}
