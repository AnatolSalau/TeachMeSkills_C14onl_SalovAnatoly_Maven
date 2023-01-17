package com.example.rest_microservice_facade.handlers;

import com.example.rest_microservice_userbackend.dto.ErrorDTO;
import com.example.rest_microservice_userbackend.exceptions.UserRuntimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * Handler for exceptions, that send ErrorDTO to client
 */
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value
            = {RuntimeException.class})
    public ResponseEntity<ErrorDTO> exceptRuntimeException(RuntimeException exception) {
        List<String> messages = new ArrayList<>();
        messages.add(exception.getMessage());
        return ResponseEntity
                . status(500)
                .body(new ErrorDTO(messages));
    }

    @ExceptionHandler(value
            = {UserRuntimeException.class })
    public ResponseEntity<ErrorDTO> exceptUserRuntimeException(UserRuntimeException exception) {
        List<String> messages = new ArrayList<>();
        messages.add(exception.getMessage());
        return ResponseEntity
                . status(500)
                .body(new ErrorDTO(messages));
    }
}