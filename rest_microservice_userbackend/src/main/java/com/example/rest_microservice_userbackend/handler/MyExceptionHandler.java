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
            = {UserRuntimeException.class, RuntimeException.class })
    public ResponseEntity<ErrorDTO> exceptError(UserRuntimeException userRuntimeException,
                                                RuntimeException exception) {
        List<String> messages = new ArrayList<>();
        messages.add(exception.getMessage());
        messages.add(userRuntimeException.getMessage());
        return ResponseEntity
                . status(400)
                .body(new ErrorDTO(messages));
    }
}