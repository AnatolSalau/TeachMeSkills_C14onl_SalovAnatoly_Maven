package com.example.spring_security_jwt_standart_without_outh.controllers;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        //Create claims
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        // @formatter:on
        //Create token with claims
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
