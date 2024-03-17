package com.educationsystem.education_sys.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AuthenticationResponse {
    private String token;
    private String role;
    private String userId;

    public AuthenticationResponse(String token,String role,String userId) {
        this.token = token;
        this.role=role;
        this.userId=userId;
    }

}