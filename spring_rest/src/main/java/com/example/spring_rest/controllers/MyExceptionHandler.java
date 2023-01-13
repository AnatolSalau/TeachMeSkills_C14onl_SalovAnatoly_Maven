package com.example.spring_rest.controllers;

import com.example.spring_rest.dto.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDTO> exceptError() {
        return ResponseEntity
                .status(400)
                .body(new ErrorDTO("ErrorDTO from exception handler"));
    }
}
