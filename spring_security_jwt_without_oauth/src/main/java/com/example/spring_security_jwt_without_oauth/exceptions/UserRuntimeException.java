package com.example.spring_security_jwt_without_oauth.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@NoArgsConstructor
@Getter
public class UserRuntimeException extends RuntimeException{
    private int statusCode;
    public UserRuntimeException(int statusCode, String message) {
        super(message) ;
        this. statusCode = statusCode;
    }
}
