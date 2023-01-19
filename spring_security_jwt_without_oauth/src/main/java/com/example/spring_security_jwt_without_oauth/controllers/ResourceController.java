package com.example.spring_security_jwt_without_oauth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class ResourceController {
    @GetMapping(path = "permitall/**")
    public ResponseEntity<String> getIndex() {
        return ResponseEntity.ok("Get index");
    }
}
