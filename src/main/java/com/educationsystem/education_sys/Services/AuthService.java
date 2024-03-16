package com.educationsystem.education_sys.Services;

import com.educationsystem.education_sys.model.User;

import java.util.Optional;

public interface AuthService {
    String authenticateUser(String email,String Password);
    public String getAuthenticatedUserEmail();
    public Optional<User> getUserByEmail(String email);
}
