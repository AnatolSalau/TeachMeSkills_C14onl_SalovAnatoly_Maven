package com.example.rest_microservice_userbackend.controllers;

import com.example.rest_microservice_userbackend.dto.UserDTO;
import com.example.rest_microservice_userbackend.services.UserDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

    @Autowired
    private UserDBService userDBService;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUsers = userDBService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping(path = "/{userLogin}")
    public ResponseEntity<UserDTO> getUserByLogin(
            @PathVariable(name = "userLogin")String userLogin
            ) {
        UserDTO userDTO1 = userDBService.getUserDTOByLogin(userLogin);
        return ResponseEntity.ok(userDTO1);
    }
}