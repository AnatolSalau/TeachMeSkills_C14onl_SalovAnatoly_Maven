package by.salov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @GetMapping(path = "/") для всех пользователей (в том числе не аутентифицированных)
 * @GetMapping(path = "/user")  для пользователей с ролью USER и ADMIN
 * @GetMapping(path = "/admin") admin для пользователей с ролью ADMIN
 */
@Controller
@RequestMapping(path = "/")
public class HomeController {

    @GetMapping
    public String getHome() {
        return "home.html";
    }

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
