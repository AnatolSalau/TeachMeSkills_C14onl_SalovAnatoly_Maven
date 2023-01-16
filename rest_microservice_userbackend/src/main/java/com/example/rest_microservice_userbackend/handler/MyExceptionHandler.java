package com.example.rest_microservice_userbackend.handler;

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
            = {RuntimeException.class, UserRuntimeException.class})
    public ResponseEntity<ErrorDTO> exceptRuntimeException(
            RuntimeException exception, UserRuntimeException userRuntimeException) {
        List<String> messages = new ArrayList<>();
        if (exception != null) {
            messages.add(exception.getMessage());
        }
        if (userRuntimeException != null) {
            messages.add(userRuntimeException.getMessage());
        }
        return ResponseEntity
                . status(400)
                .body(new ErrorDTO(messages));
    }
}