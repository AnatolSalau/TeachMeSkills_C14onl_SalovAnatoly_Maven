package com.example.spring_security_jwt_without_oauth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/")
public class PermitAllController {
    @GetMapping()
    public ResponseEntity<String> getIndex() {
        return ResponseEntity.ok("Get index");
    }
    @PostMapping()
    public ResponseEntity<String> postIndex() {
        return ResponseEntity.ok("Post index");
    }

    @GetMapping(path = "permitall/**")
    public ResponseEntity<String> getPermitAll() {
        return ResponseEntity.ok("Get PermitAll");
    }
    @PostMapping(path = "permitall/**")
    public ResponseEntity<String> postPermitAll() {
        return ResponseEntity.ok("Post PermitAll");
    }
    @GetMapping(path = "ignoring/**")
    public ResponseEntity<String> getIgnoringAll() {
        return ResponseEntity.ok("Get Ignorin");
    }
    @PostMapping(path = "ignoring/**")
    public ResponseEntity<String> postIgnoringAll() {
        return ResponseEntity.ok("Post Ignorin");
    }
    @GetMapping(path = "test/**")
    public ResponseEntity<String> getTest() {
        return ResponseEntity.ok("Get Test");
    }
}
