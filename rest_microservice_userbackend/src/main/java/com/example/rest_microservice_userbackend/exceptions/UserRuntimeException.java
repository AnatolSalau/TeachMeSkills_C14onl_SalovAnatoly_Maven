package com.example.rest_microservice_userbackend.exceptions;

public class UserRuntimeException extends RuntimeException{
    public UserRuntimeException(String message) {
        super(message);
    }
}
