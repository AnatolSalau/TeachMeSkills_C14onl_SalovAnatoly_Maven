package com.example.rest_microservice_facade.handlers;


import com.example.rest_microservice_facade.dto.ErrorDTO;
import com.example.rest_microservice_facade.exception.UserRuntimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * Handler for exceptions, that send ErrorDTO to client
 * !!!!
 * Handle Runtime exception from RestTemplate
 */
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value
            = {RuntimeException.class})
    public ResponseEntity<ErrorDTO> exceptRuntimeException(RuntimeException exception) {
        String message = exception.getMessage();
        return ResponseEntity
                . status(500)
                .body(new ErrorDTO(500, message));
    }

    @ExceptionHandler(value
            = {UserRuntimeException.class })
    public ResponseEntity<ErrorDTO> exceptUserRuntimeException(UserRuntimeException exception) {
        String message = exception.getMessage();
        return ResponseEntity
                . status(500)
                .body(new ErrorDTO(500, message));
    }
}