package by.salov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class UserController {
    @GetMapping
    public String getUser() {
        System.out.println("Hello from UserController");
        return "user.html";
    }
}
