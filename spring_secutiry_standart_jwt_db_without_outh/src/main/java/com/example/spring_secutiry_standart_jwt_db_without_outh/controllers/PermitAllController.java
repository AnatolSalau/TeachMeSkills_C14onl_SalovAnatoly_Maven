package com.example.spring_secutiry_standart_jwt_db_without_outh.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/")
public class PermitAllController {

    @GetMapping(path = "permitall/**")
    public ResponseEntity<String> getPermitAll() {
        return ResponseEntity.ok("Get PermitAll");
    }

    @PostMapping(path = "permitall/**")
    public ResponseEntity<String> postPermitAll() {
        return ResponseEntity.ok("Post PermitAll");
    }

}
