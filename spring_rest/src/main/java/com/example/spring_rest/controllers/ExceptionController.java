package com.example.spring_rest.controllers;

import com.example.spring_rest.dto.ErrorDTO;
import com.example.spring_rest.services.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Just controller that execute exceptionService.doSmth() that throw RuntimeException
 */
@RestController
@RequestMapping(path = "/api/exception")
public class ExceptionController {

    @Autowired
    private  ExceptionService exceptionService;

    @GetMapping()
    public void getException() {
        //throw runtime exception
        exceptionService.doSmth();
    }
}
