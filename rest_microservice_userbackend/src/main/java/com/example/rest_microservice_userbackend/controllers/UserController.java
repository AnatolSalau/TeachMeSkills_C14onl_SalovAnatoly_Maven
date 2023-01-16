package com.example.rest_microservice_userbackend.controllers;

import com.example.rest_microservice_userbackend.dto.UserDTO;
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

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        UserDTO userDTO1 = new UserDTO(UUID.randomUUID(), "login1",
                "password1", "email1@mail.com"
                );
        UserDTO userDTO2 = new UserDTO(UUID.randomUUID(), "login2",
                "password2", "email2@mail.com"
        );
        List<UserDTO> list = new ArrayList<>();
        list.add(userDTO1);
        list.add(userDTO2);

        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/{userLogin}")
    public ResponseEntity<UserDTO> getUserByLogin(
            @PathVariable(name = "userLogin")String userLogin
            ) {
        UserDTO userDTO1 = new UserDTO(UUID.randomUUID(), "login1",
                "password1", "email1@mail.com"
        );
        userDTO1.setLogin(userLogin);
        return ResponseEntity.ok(userDTO1);
    }
}