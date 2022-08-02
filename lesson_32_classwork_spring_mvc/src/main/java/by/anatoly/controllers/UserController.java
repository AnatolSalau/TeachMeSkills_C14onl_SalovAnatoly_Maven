package by.anatoly.controllers;

import by.anatoly.exceptions.FirstException;
import by.anatoly.exceptions.SecondException;
import by.anatoly.model.User;
import by.anatoly.services.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class UserController {
    //Add annotation @Autowired for sturtup load UserLoginService
    @Autowired
    UserLoginService userLoginService;

    @GetMapping()
    public ModelAndView getUserPage(ModelAndView modelAndView) {
        System.out.println("---------------Get user page------------------");
        modelAndView.setViewName("user");
        return modelAndView;
    }
    //We uses @Valid annotation for check fields that mark by annotation @NotEmpty
    //We uses BindingResult for handling errors
    @PostMapping()
    public ModelAndView postUserPage(
            @Valid User user,
             BindingResult bindingResult,
             @RequestParam(name = "email")String emailUser,
             @RequestParam(name = "password")String passwordUser,
            ModelAndView modelAndView
    ) throws FirstException, SecondException {
        modelAndView.setViewName("user");
        System.out.println(user.getEmail() + " / " + user.getPassword());
        modelAndView.addObject("user1");
        System.out.println("---------------Post user page------------------");
        //Get all errors from BindingResult
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError error : fieldErrors) {
            //Name of field with error
            String field = error.getField();
            //Error message
            String defaultMessage = error.getDefaultMessage();
            System.out.println(field);
            System.out.println(defaultMessage);

        }
        System.out.println("Start check error");
        //userLoginService.checkLogin(user);
        if (passwordUser.equals("1111")) {
            throw new FirstException("FirstException Password is 1111");
        }
        if (passwordUser.equals("2222")) {
            throw new SecondException("SecondException Password is 2222");
        }
        return  modelAndView;
    }

    @ExceptionHandler(NestedServletException.class)
    public String handler(Exception exception, ModelAndView modelAndView) {
        modelAndView.addObject("exception", exception.getMessage());
        Object exception1 = modelAndView.getModel().get("exception");
        System.out.println("Exception form handler: " + exception1.toString());
        return exception.getMessage();
    }
}
