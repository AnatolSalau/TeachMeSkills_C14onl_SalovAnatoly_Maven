package com.example.spring_security_jwt_without_oauth.pojo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class AuthRequest {
    private String name;
    private String password;
}
