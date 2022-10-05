package by.salov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @GetMapping(path = "/admin")
    public String getAdmin() {
        System.out.println("Hello from getAdmin()");
        return "admin.html";
    }

    @GetMapping(path = "/user")
    public String getUser() {
        System.out.println("Hello from getUser()");
        return "user.html";
    }
}
