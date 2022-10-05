package by.salov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/login")
public class LoginController {

    @GetMapping
    public ModelAndView getLogin() {
        ModelAndView modelAndView = new ModelAndView("/login.html");
        modelAndView.addObject("title", new String("Форма ввода"));
        return modelAndView;
    }
}
