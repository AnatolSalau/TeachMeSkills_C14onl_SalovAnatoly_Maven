package by.salov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/")
public class HomeController {
    @PostConstruct
    @GetMapping(path = "/user")
    public String user() {
        return "user.html";
    }
    @PostConstruct
    @GetMapping(path = "/admin")
    public String admin() {
        return "admin.html";
    }
}
