package com.example.rest_microservice_facade_feignclient.exception;

import lombok.Getter;

@Getter
public class UserRuntimeException extends RuntimeException {

    private int statusCode;

    public UserRuntimeException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
