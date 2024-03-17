package com.educationsystem.education_sys.Services;

import com.educationsystem.education_sys.dto.AuthenticationResponse;
import com.educationsystem.education_sys.dto.LoginRequest;
import com.educationsystem.education_sys.model.Role;
import com.educationsystem.education_sys.model.User;
import com.educationsystem.education_sys.dto.UserDto;
import com.educationsystem.education_sys.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserDto request){
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(mapStringToRole(request.getRole()));
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token,user.getRole().toString(),user.getUserId());
    }
    public AuthenticationResponse authenticate(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getUsername());
        String role = user.getRole().toString();
        String userId = user.getUserId();
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token,role,userId);
    }
    private Role mapStringToRole(String roleString) {
        return switch (roleString.toLowerCase()) {
            case "student" -> Role.student;
            case "instructor" -> Role.instructor;
            default -> throw new IllegalArgumentException("Invalid role: " + roleString);
        };
    }
}
