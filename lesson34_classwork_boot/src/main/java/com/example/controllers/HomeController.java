package com.example.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class HomeController {
    //Get value of property app.url from application yaml
    @Value("${app.url}")
    String url;
    @GetMapping
    public String home() {
        System.out.println(url);
        System.out.println("hello from home jsp");
        return "home";
    }
}
