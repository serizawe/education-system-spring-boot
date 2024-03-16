package com.educationsystem.education_sys.controllers;

import com.educationsystem.education_sys.Services.AuthService;
import com.educationsystem.education_sys.Services.UserService;
import com.educationsystem.education_sys.model.User;
import com.google.api.client.util.Value;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    @PostMapping("/api/user/login")
    public ResponseEntity<?> login(@RequestBody String email,@RequestBody String password){
        try {
            String token = authService.authenticateUser(email, password);
            User user = userService.getUserByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
            Map<String,Object> response = new HashMap<>();
            response.put("token",token);
            response.put("userId",user.getId());
            response.put("role",user.getRole());
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

    }

}
