package com.example.spring_secutiry_standart_jwt_db_without_outh.exceptions;

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
