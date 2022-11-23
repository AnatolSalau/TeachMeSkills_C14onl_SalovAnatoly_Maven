package by.salov.lesson29_spring_aop.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class SqlExeptionHandler {

    @ExceptionHandler(SQLException.class)
    public void SqlExceptionHandler(Exception exception) {
        System.out.println("_____________________________________________________________________");
        exception.printStackTrace();
        System.out.println("_____________________________________________________________________");
    }
}
