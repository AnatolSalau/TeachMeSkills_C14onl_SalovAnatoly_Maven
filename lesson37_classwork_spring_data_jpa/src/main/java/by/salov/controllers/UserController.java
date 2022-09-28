package by.salov.controllers;

import by.salov.entity.User;
import by.salov.entity.UserDTO;
import by.salov.services.DTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class UserController {

    @Autowired
    private DTOService dtoService;

    @GetMapping
    public ModelAndView getUser() {
        ModelAndView modelAndView = new ModelAndView("user.html");
        User user = new User("FirstUser", 32, "7-Jun-2013");
        System.out.println("Hello from UserController");
        UserDTO userDTO = dtoService.getUserDTO(user);
        System.out.println(userDTO);
        modelAndView.addObject("user",userDTO);
        return modelAndView;
    }
}
