package com.example.spring_security_jwt_without_oauth.controllers;

import com.example.spring_security_jwt_without_oauth.pojo.AuthRequest;
import com.example.spring_security_jwt_without_oauth.pojo.AuthResponse;
import com.example.spring_security_jwt_without_oauth.pojo.UserDetailsImpl;
import com.example.spring_security_jwt_without_oauth.services.JWTUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * This is where the JWT token is actually issued.
 * The user makes a POST request with a username and password to /authenticate,
 * and receives a generated token in response. The token is generated by the generateToken() method
 * from JWTUtilService.java.
 */
@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationProvider authenticationProvider;
    //Or we can can use AuthenticationManager
    //private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtilService jwtTokenUtil;

    @PostMapping("/authenticate/")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication;
        try {
            authentication = authenticationProvider
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    authRequest.getName(),
                                    authRequest.getPassword()
                                )
                    );
            System.out.println(authentication);
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "UNAUTHORIZED : Name and password are wrong", e);
        }
        // when creating a token, the username is put in it as a Subject claim(claim subject)
        // and the list of authorities as a custom claim(claim authorities)
        String jwt = jwtTokenUtil.generateToken((UserDetailsImpl) authentication.getPrincipal());
        return new AuthResponse(jwt);
    }
}