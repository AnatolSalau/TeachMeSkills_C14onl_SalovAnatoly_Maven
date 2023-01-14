package com.example.spring_rest.controllers;

import com.example.spring_rest.dto.Gender;
import com.example.spring_rest.dto.SearchDTO;
import com.example.spring_rest.dto.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/api/user")
@Tag(name = "User controller",
        description = "User CRUD operations" )
public class UserController {

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        User user1 = new User(1L,"user",
                "1111", Gender.MALE, new Date());
        User user2 = new User(2L,"user",
                "1111", Gender.MALE, new Date());
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {
        System.out.println("Get by id : " + userId);
        User user = new User(1L,"user",
                "1111", Gender.MALE, new Date());
        user.setId(userId);
        //Cache control
        return ResponseEntity
                .status(200)
                .cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS))
                .body(user);
    }

    @GetMapping("/error/{userId}")
    public ResponseEntity<User> getErrorById(@PathVariable("userId") Long userId) {

        User user = new User(1L,"user",
                "1111", Gender.MALE, new Date());
        user.setId(userId);
        if(userId == 0L) {
            System.out.println("Get error by id : " + userId);
            //Return 400 status code to client
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.ok(user);
    }


    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        System.out.println(user);
        //Add custom header
        return ResponseEntity
                .status(201)
                .header("testHeader", "user was created")
                .body(user);
    }
    //search user by post
    @PostMapping(path = "/search")
    public ResponseEntity<List<User>> searchUser(@RequestBody SearchDTO searchDTO) {
        System.out.println("SearchDTO : " + searchDTO);
        User user1 = new User(searchDTO.getMinId(), searchDTO.getLogin(),
                "1111", searchDTO.getGender(), new Date());
        User user2 = new User(searchDTO.getMaxId(), searchDTO.getLogin(),
                "2222", searchDTO.getGender(), new Date());
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        //Add custom header
        return ResponseEntity
                .status(200)
                .body(users);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        System.out.println("User : " + user + " was update");
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> delete(@PathVariable("userId") Long userId) {
        System.out.println("Delete user with id : " + userId);
        User user = new User(1L,"user",
                "1111", Gender.MALE, new Date());
        user.setId(userId);
        return ResponseEntity.ok(user);
    }
}
