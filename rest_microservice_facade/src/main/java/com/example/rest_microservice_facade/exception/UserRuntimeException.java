package com.example.rest_microservice_facade.exception;

public class UserRuntimeException extends RuntimeException{
    public UserRuntimeException(String message) {
        super(message);
    }
}
