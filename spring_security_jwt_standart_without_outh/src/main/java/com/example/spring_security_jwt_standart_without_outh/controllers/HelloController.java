package com.example.spring_security_jwt_standart_without_outh.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller for the hello resource.
 * / Header Authorization : Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoidXNlciIsImV4cCI6MTY3NTcwNTk3NCwiaWF0IjoxNjc1NjY5OTc0LCJzY29wZSI6ImFwcCJ9.d5Ud_WevmbdIdKqssTeHyxfIpiOmuKtHa3PpYbifEB7fz7_DcHBx65KgcuWxyVx38BBI39cY-mSYa-dKZnRYWLqzG_CK4CWHap4ClDMyBpjP_O7Q0uHqRjkdCuC4cZQE2GZqwJuo5V4mmnREdrSGMXcHPizaRfERj-DZJEaNA1Y1Mcm6cJCHXHk7kzQI0LnBgg0w02ZJa68VJ_O3lJMulKPqrFZkKFjelBuZ0NgEkJLp5T3rBkWJGBsCI3Itvcubo0J5qTtsj1659V8TIKZkVcdFAfkWkXXQXF1G2raY9kXsQ9bkqSjnEHQLugFL7StTelmZHxpr8nb0Kc9TptKLvA
 */
@RestController
public class HelloController {

    @GetMapping("/")
    public String hello(Authentication authentication) {
        return "Hello, " + authentication.getName() + "!";
    }
}