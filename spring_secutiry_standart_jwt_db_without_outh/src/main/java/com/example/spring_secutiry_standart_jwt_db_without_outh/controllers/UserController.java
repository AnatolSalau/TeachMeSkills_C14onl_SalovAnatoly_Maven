package com.example.spring_secutiry_standart_jwt_db_without_outh.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
"/api/v1/users/**"
 */
@RestController
@RequestMapping(path = "/users/")
public class UserController {

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<String> getUser() {

        return ResponseEntity.ok("Get user");
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<String> postUser() {
        return ResponseEntity.ok("Post user");
    }
}
