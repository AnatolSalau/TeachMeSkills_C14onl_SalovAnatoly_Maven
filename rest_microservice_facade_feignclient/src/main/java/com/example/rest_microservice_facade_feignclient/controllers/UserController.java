package com.example.rest_microservice_facade_feignclient.controllers;

import com.example.rest_microservice_facade_feignclient.dto.UserDTO;
import com.example.rest_microservice_facade_feignclient.services.UserLinkFeignClientService;
import com.example.rest_microservice_facade_feignclient.services.UserLinkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {

    @Autowired
    private UserLinkFeignClientService userLinkFeignClientService;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUsers = userLinkFeignClientService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping(path = "/{userLogin}")
    public ResponseEntity<UserDTO> getUserByLogin(
            @PathVariable(name = "userLogin") String userLogin
    ) {
        UserDTO userByLogin = userLinkFeignClientService.getUserByLogin(userLogin);

        return ResponseEntity.ok(userByLogin);
    }

    @GetMapping(path = "/id/{userId}")
    public ResponseEntity<UserDTO> getUserById(
            @PathVariable(name = "userId") String userId
    ) {
        UserDTO userById = userLinkFeignClientService.getUserById(UUID.fromString(userId));

        return ResponseEntity.ok(userById);
    }

    @PostMapping()
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        UserDTO userDTOFromDb = userLinkFeignClientService.saveUser(userDTO);
        return ResponseEntity.ok(userDTOFromDb);
    }
}