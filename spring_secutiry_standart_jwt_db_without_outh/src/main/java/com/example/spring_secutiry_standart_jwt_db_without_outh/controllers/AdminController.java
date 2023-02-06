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
@RequestMapping(path = "/admins/")
public class AdminController {
    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> getAdmin() {
        return ResponseEntity.ok("Get admin");
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> postAdmin() {
        return ResponseEntity.ok("Post admin");
    }
}
