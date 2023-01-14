package com.example.spring_rest.controllers;

import com.example.spring_rest.services.ExceptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Just controller that execute exceptionService.doSmth() that throw RuntimeException
 */
@RestController
@RequestMapping(path = "/api/exception")
@Tag(name = "Test exception controller",
        description = "Controller for testing RestAdviceController")
public class ExceptionController {

    @Autowired
    private  ExceptionService exceptionService;

    @GetMapping()
    @Tag(name = "get exception", description = "get exception for test")
    public void getException() {
        //throw runtime exception
        exceptionService.doSmth();
    }
}
