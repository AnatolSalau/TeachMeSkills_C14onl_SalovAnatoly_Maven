package com.example.rest_microservice_facade_feignclient.handlers;


import com.example.rest_microservice_facade_feignclient.dto.ErrorDTO;
import com.example.rest_microservice_facade_feignclient.exception.UserRuntimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
        int statusCode = exception.getStatusCode();
        String message = exception.getMessage();
        return ResponseEntity
                . status(500)
                .body(new ErrorDTO(statusCode, message));
    }
}