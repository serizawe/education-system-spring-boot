package com.educationsystem.education_sys.Services;
import com.educationsystem.education_sys.model.User;
import com.educationsystem.education_sys.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UserService {

    User getUser(Long id);
    User createUser(User user);

    Optional<User> getUserByEmail(String email);
}
