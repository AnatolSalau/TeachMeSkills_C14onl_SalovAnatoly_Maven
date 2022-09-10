package by.salov.lesson43_thymeleafe_classwork.controllers;


import by.salov.lesson43_thymeleafe_classwork.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class RootController {
    @GetMapping
    public String getHome() {
        User user = new User();
        System.out.println("Hello from root controller");
        return "user.html";
    }
}
