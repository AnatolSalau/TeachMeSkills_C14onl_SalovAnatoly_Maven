package com.example.spring_security_jwt_without_oauth.controllers;

import com.example.spring_security_jwt_without_oauth.services.JWTUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtilService jwtTokenUtil;
}
