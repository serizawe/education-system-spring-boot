package com.educationsystem.education_sys.Services;
import com.educationsystem.education_sys.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class InstructorActionsService {
    private final UserRepository userRepository;

}
