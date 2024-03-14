package com.educationsystem.education_sys.controllers;

import com.educationsystem.education_sys.Services.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EnrollmentController {
    @Autowired
    private final EnrollmentService enrollmentService;
}
