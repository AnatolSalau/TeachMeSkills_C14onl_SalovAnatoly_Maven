package com.example.rest_microservice_facade.controllers;

import com.example.rest_microservice_facade.dto.UserDTO;
import com.example.rest_microservice_facade.services.UserLinkService;
import com.example.rest_microservice_facade.services.UserLinkServiceImpl;
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
    private  UserLinkServiceImpl userLinkService;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUsers = userLinkService.getAllUsers();

        return ResponseEntity.ok(allUsers);
    }

    @GetMapping(path = "/{userLogin}")
    public ResponseEntity<UserDTO> getUserByLogin(
            @PathVariable(name = "userLogin")String userLogin
            ) {
        UserDTO userByLogin = userLinkService.getUserByLogin(userLogin);
        return ResponseEntity.ok(userByLogin);
    }

}