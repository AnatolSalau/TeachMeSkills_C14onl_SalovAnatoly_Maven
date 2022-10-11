package by.salov.lesson45_spring_security_roles.controllers;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionController{

    @ExceptionHandler({ AuthenticationException.class })
    public String handleAuthenticationException(Exception ex) {

        ex.printStackTrace();
        return null;
    }
}