package com.example.rest_microservice_userbackend_feignlient.controllers;

import com.example.rest_microservice_userbackend_feignlient.dto.UserDTO;
import com.example.rest_microservice_userbackend_feignlient.services.UserDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v2/user")
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
            @PathVariable(name = "userLogin") String userLogin
    ) {
        UserDTO userDTO1 = userDBService.getUserDTOByLogin(userLogin);

        return ResponseEntity.ok(userDTO1);
    }

    @GetMapping(path = "/id/{userId}")
    public ResponseEntity<UserDTO> getUserById(
            @PathVariable(name = "userId") String userId
    ) {
        UserDTO userById = userDBService.getUserDTOById(UUID.fromString(userId));

        return ResponseEntity.ok(userById);
    }

    @PostMapping()
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        UserDTO userDTOFromDb = userDBService.saveUser(userDTO);
        return ResponseEntity.ok(userDTOFromDb);
    }
}