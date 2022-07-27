package by.salov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView getHome(
    ) {
        System.out.println("---------------GET HOME-----------------");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test.html");
        return modelAndView;

    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String postHome() {
        System.out.println("---------------POST HOME-----------------");
        return "postHome.jsp";
    }
}
