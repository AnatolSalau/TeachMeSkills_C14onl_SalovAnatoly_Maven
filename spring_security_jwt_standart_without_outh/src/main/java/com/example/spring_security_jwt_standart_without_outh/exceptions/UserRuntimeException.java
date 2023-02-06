package com.example.spring_security_jwt_standart_without_outh.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRuntimeException extends RuntimeException{
    private int statusCode;
    public UserRuntimeException(int statusCode, String message) {
        super(message) ;
        this. statusCode = statusCode;
    }
}
