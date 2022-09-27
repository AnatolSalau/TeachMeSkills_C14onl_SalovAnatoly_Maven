package by.salov.controllers;

import by.salov.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class UserController {
    @GetMapping
    public ModelAndView getUser() {
        ModelAndView modelAndView = new ModelAndView("user.html");
        User user = new User("FirstUser", 32);
        modelAndView.addObject("user",user);
        System.out.println("Hello from UserController");
        return modelAndView;
    }
}
