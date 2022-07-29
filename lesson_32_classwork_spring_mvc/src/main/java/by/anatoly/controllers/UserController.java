package by.anatoly.controllers;

import by.anatoly.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/")
public class UserController {

    @GetMapping()
    public ModelAndView getUserPage(ModelAndView modelAndView) {
        System.out.println("---------------Get user page------------------");
        modelAndView.setViewName("user");
        return modelAndView;
    }
    @PostMapping()
    public ModelAndView postUserPage(
            @RequestParam(name = "email")String emailUser,
            @RequestParam(name = "password")String passwordUser,
            ModelAndView modelAndView
    ) {
        modelAndView.setViewName("user");
        modelAndView.addObject("user1", new @Valid()User(emailUser,passwordUser));
        System.out.println("---------------Post user page------------------");
        return  modelAndView;
    }
}
