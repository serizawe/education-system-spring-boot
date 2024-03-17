package com.educationsystem.education_sys.Services;
import com.educationsystem.education_sys.model.User;
import com.educationsystem.education_sys.dto.UserDto;

import java.util.Optional;


public interface UserService {

    Optional<User> getUser(String userId);
    User createUser(UserDto userDto);

    Optional<User> getUserByEmail(String email);
}
