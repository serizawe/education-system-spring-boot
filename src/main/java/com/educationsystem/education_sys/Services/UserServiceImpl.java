package com.educationsystem.education_sys.Services;
import com.educationsystem.education_sys.repositories.UserRepository;
import com.educationsystem.education_sys.model.User;
import com.educationsystem.education_sys.model.Role;
import com.educationsystem.education_sys.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    @Autowired
    private final UserRepository userRepository;
    @Override
    public Optional<User> getUser(String userId){
        return userRepository.findById(userId);
    }

    @Override
    public User createUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }
        String userId = UUID.randomUUID().toString();
        Role role;
        if ("student".equalsIgnoreCase(userDto.getRole())) {
            role = Role.student;
        } else if ("instructor".equalsIgnoreCase(userDto.getRole())) {
            role = Role.instructor;
        } else {
            throw new IllegalArgumentException("Invalid role: " + userDto.getRole());
        }
        User user = new User();
        user.setUserId(userId);
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
        user.setRole(role);
        return userRepository.save(user);
    }


    public Optional<User> getUserByEmail(String email){
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

}
