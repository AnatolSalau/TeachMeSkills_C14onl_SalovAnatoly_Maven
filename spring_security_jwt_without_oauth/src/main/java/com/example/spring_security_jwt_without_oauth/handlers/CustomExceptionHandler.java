package com.example.spring_security_jwt_without_oauth.handlers;

import com.example.spring_security_jwt_without_oauth.dto.ErrorDTO;
import com.example.spring_security_jwt_without_oauth.exceptions.UserRuntimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value
            = {RuntimeException. class})
    public ResponseEntity<ErrorDTO>
    exceptRuntimeException(RuntimeException exception) {
        String message = exception.getMessage() ;
        return ResponseEntity
                . status(500)
                .body(new ErrorDTO(500, message)) ;
    }
    @ExceptionHandler(value
            = {UserRuntimeException. class})
    public ResponseEntity<ErrorDTO>
    exceptUserRuntimeException(UserRuntimeException exception) {
        int statusCode = exception.getStatusCode() ;
        String message = exception.getMessage() ;
        return ResponseEntity
                . status(500)
                .body(new ErrorDTO(statusCode, message)) ;
    }
}
