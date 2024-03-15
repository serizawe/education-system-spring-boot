package com.educationsystem.education_sys.repositories;
import com.educationsystem.education_sys.model.User;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
