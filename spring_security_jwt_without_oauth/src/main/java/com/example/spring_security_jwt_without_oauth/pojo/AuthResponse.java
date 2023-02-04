package com.example.spring_security_jwt_without_oauth.pojo;

import lombok.*;

/**
 * JWT authenticate response for JWT token
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class AuthResponse {
    private String jwtToken;
}
