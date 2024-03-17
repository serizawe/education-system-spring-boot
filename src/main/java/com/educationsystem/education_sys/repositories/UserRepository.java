package com.educationsystem.education_sys.repositories;
import com.educationsystem.education_sys.model.User;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String email);

    Optional<User> findByUserId(String userId);

    User findByUsername(String username);
}
