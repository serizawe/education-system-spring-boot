package com.educationsystem.education_sys.controllers;
import com.educationsystem.education_sys.model.User;
import com.educationsystem.education_sys.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<User> getUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<User> createUser(@PathVariable User user){
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser,HttpStatus.OK);
    }


}
