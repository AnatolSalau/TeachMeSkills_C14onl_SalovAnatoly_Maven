package com.example.spring_rest.controllers;

import com.example.spring_rest.dto.Gender;
import com.example.spring_rest.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @GetMapping
    public User getAllUsers() {
        User user = new User(1L,"user",
                "1111", Gender.MALE, new Date());
        return user;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        System.out.println(user);
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        System.out.println("User : " + user + " was update");
        return user;
    }
}
