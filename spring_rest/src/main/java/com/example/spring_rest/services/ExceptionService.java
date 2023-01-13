package com.example.spring_rest.services;

import org.springframework.stereotype.Service;

/**
 * just service, that throw RuntimeException
 */
@Service
public class ExceptionService {
    public void doSmth() {
        System.out.println("ExceptionService throw new RuntimeException");
        throw new RuntimeException();
    }
}
