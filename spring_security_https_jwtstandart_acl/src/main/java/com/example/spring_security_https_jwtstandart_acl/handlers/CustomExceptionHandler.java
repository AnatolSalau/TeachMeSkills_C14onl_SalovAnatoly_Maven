package com.example.spring_security_https_jwtstandart_acl.handlers;

import com.example.spring_security_https_jwtstandart_acl.dto.ErrorDTO;
import com.example.spring_security_https_jwtstandart_acl.exceptions.CustomRuntimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
      @ExceptionHandler(value
            = {RuntimeException. class})
      public ResponseEntity<ErrorDTO>
      exceptRuntimeException(RuntimeException exception)
      {
            System. out.println("ServerExceptionHandler exceptRuntimeException") ;
                  String message = exception.getMessage() ;
            return ResponseEntity
                  . status(500)
                  .body(new ErrorDTO(500,
                        "CustomExceptionHandler exceptRuntimeException" +
                              message)) ;
      }
      @ExceptionHandler(value
            = {CustomRuntimeException. class})
      public ResponseEntity<ErrorDTO>
      exceptUserRuntimeException(CustomRuntimeException exception) {
            System. out.println("ServerExceptionHandler exceptUserRuntimeException") ;
            int statusCode = exception.getStatusCode() ;
            String message = exception.getMessage() ;
            return ResponseEntity
                  . status(500)
                  .body(new ErrorDTO(statusCode,
                        "CustomExceptionHandler exceptUserRuntimeException " +
                              message)) ;
      }
}
