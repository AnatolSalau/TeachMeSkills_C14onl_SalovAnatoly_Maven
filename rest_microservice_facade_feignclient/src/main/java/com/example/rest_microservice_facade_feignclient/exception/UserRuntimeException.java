package com.example.rest_microservice_facade_feignclient.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRuntimeException extends RuntimeException {

    private int statusCode;

    public UserRuntimeException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
