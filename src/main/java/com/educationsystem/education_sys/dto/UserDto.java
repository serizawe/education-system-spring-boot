package com.educationsystem.education_sys.dto;

import com.educationsystem.education_sys.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserDto {

    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String role;

}
