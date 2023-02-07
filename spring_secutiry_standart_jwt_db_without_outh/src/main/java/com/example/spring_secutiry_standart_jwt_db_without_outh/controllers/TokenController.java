package com.example.spring_secutiry_standart_jwt_db_without_outh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A controller for the token resource.
 * return JWT token
 * /token - Header: Authorization : Basic dXNlcjpwYXNzd29yZA==
 */
@RestController
public class TokenController {

    @Autowired
    JwtEncoder encoder;

    @PostMapping("/token")
    public String token(Authentication authentication) {
        //Obtains the current instant from the system clock.
        Instant now = Instant.now();

        // value in ms - the end of token
        long expiry = 36000L;
        // @formatter:off
        //Get all authorities (roles)
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        //Create claims
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("name", authentication.getName())
                .claim("authorities", authorities)
                .build();
        // @formatter:on
        //Create token with claims
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
