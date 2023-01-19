package com.example.spring_security_jwt_without_oauth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
"/api/v1/users/**"
 */
@RestController
@RequestMapping(path = "/api/v1/admins/")
public class AdminController {
    @GetMapping()
    public ResponseEntity<String> getAdmin() {
        return ResponseEntity.ok("Get admin");
    }
    @PostMapping()
    public ResponseEntity<String> postAdmin() {
        return ResponseEntity.ok("Post admin");
    }
    @GetMapping(path = "test")
    public ResponseEntity<String> getAdminTest() {
        return ResponseEntity.ok("Get admin test");
    }
}
