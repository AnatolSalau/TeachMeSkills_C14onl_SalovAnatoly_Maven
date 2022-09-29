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

import java.util.List;

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
        User user1 = new User("FirstUser", 30, "10-Jun-2013");
        User user2 = new User("SecondUser", 30, "20-Jun-2013");
        User user3 = new User("ThirdUser", 30, "30-Jun-2013");
        boolean isSave = dbService.saveUsersInDB(user1, user2, user3);
        List<User> usersByName = dbService.getUsersByName(20);
        System.out.println(usersByName);
        User userFromDB = dbService.getUserFromDBByID(1L);
        UserDTO userDTO = dtoService.getUserDTO(userFromDB);
        System.out.println("userDTO: " + userDTO);
        System.out.println("isSave: " + isSave);
        modelAndView.addObject("userFromDB",userDTO);
        System.out.println(dbService.deleteFromBDByID(3L));
        return modelAndView;
    }
}
