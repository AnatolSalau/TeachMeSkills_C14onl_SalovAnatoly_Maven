package by.salov.controllers;

import by.salov.entity.User;
import by.salov.entity.UserDTO;
import by.salov.services.DBService;
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
    DTOService dtoService;

    @Autowired
    DBService dbService;

    @GetMapping
    public ModelAndView getUser() {
        ModelAndView modelAndView = new ModelAndView("user.html");
        User user = new User("FirstUser", 32, "7-Jun-2013");
        boolean isSave = dbService.saveUserInDB(user);

        User userFromDB = dbService.getUserFromDB(1L);
        UserDTO userDTO = dtoService.getUserDTO(userFromDB);
        System.out.println("userDTO: " + userDTO);
        System.out.println("isSave: " + isSave);
        modelAndView.addObject("userFromDB",userDTO);
        return modelAndView;
    }
}
