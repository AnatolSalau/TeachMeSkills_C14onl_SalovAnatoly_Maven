package by.anatolysalov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class UserController {

    @GetMapping()
    public ModelAndView getUserPage(ModelAndView modelAndView) {
        System.out.println("---------------Get user page------------------");
        modelAndView.setViewName("user");
        return modelAndView;
    }
}
