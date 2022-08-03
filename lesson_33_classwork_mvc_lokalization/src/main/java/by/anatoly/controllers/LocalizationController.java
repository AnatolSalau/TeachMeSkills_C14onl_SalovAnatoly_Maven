package by.anatoly.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/localization")
public class LocalizationController {
    @GetMapping
    public ModelAndView getLocalization(ModelAndView modelAndView) {
        modelAndView.setViewName("localization");
        return modelAndView;
    }
}
