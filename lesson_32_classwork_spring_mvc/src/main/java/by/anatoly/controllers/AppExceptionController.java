package by.anatoly.controllers;

import by.anatoly.exceptions.FirstException;
import by.anatoly.exceptions.SecondException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

@ControllerAdvice
public class AppExceptionController {
    //Method for handling all error what may come to controller from all component

    @ExceptionHandler(NestedServletException.class)
    public ModelAndView exeptionCustomHandler(Exception exception, ModelAndView modelAndView)
    {
        System.out.println("FirstException start");
        if ( exception instanceof FirstException) {
            System.out.println("FirstException");
        }
        if ( exception instanceof SecondException) {
            System.out.println("SecondException");
        }
        modelAndView.setViewName("error");
        modelAndView.addObject("exception1", exception.getMessage());
        return modelAndView;
    }
}
