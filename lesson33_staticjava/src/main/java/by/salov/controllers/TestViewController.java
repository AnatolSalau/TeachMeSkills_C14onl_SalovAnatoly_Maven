package by.salov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Controller
public class TestViewController {

    @GetMapping("/getview")
    public ModelAndView getView(ModelAndView modelAndView, @RequestParam("carname") String carname) {
        System.out.println(carname);
        modelAndView.setViewName("resources/html/test.html");
        return modelAndView;
    }

}
