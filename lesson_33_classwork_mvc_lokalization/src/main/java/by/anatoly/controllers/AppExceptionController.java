package by.anatoly.controllers;

import by.anatoly.exceptions.FirstException;
import by.anatoly.exceptions.SecondException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.NestedServletException;

//Controller for Errorhandling
@EnableWebMvc
@ControllerAdvice
public class AppExceptionController {
    //Method for handling all error what may come to controller from all component
    @ExceptionHandler(FirstException.class)
    public String firstExceptionHandler(Exception exception, Model model)
    {
        model.addAttribute("error",exception.getMessage());
        System.out.println("firstExceptionHandler");
        System.out.println("error "+ exception.getMessage());
        return "error";
    }
    @ExceptionHandler(SecondException.class)
    public String secondExceptionHandler(Exception exception,Model model )
    {
        model.addAttribute("error",exception.getMessage());
        System.out.println("secondExceptionHandler");
        System.out.println("error "+ exception.getMessage());
        return "error";
    }
}
