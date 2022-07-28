package by.salov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @GetMapping()
    public ModelAndView getHome(
    ) {
        System.out.println("---------------GET HOME-----------------");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("getHome");
        return modelAndView;
    }
    @PostMapping
    public String postHome() {
        System.out.println("---------------POST HOME-----------------");
        return "/WEB-INF/pages/post 1Home.jsp";
    }
}
