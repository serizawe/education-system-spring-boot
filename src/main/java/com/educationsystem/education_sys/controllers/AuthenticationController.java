package com.educationsystem.education_sys.controllers;

import com.educationsystem.education_sys.Services.AuthenticationService;
import com.educationsystem.education_sys.dto.AuthenticationResponse;
import com.educationsystem.education_sys.dto.LoginRequest;
import com.educationsystem.education_sys.dto.UserDto;
import com.educationsystem.education_sys.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/api/user/sign-up")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto request){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/api/auth/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){
        System.out.println(request);
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}