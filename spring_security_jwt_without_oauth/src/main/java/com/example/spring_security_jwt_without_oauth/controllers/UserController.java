package com.example.spring_security_jwt_without_oauth.controllers;

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
@RequestMapping(path = "/api/v1/users/")
public class UserController {

    @GetMapping()
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> getUser() {

        return ResponseEntity.ok("Get user");
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> postUser() {

        return ResponseEntity.ok("Post user");
    }
}
