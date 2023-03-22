package com.example.spring_security_https_jwtstandart_acl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
public class AuthenticationController {
      @Autowired
      JwtEncoder encoder;

      @PostMapping("/authenticate")
      public String token(Authentication authentication) {
            //Obtains the current instant from the system clock.
            Instant now = Instant.now();

            // value in ms - the end of token
            long expiry = 36000L;
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
            //Create token with claims
            String tokenValue = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
            return tokenValue;
      }
}
