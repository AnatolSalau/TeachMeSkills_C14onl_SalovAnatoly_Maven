package by.salov.lesson49_testing.controllers;

import by.salov.lesson49_testing.domain.User;
import by.salov.lesson49_testing.exception.UserAllreadyExistExeption;
import by.salov.lesson49_testing.exception.UserNotExist;
import by.salov.lesson49_testing.services.UserService;
import by.salov.lesson49_testing.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class GetUserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/all")
    public List<User> getUserList() {
        return userServiceImpl.getAllUsers();
    }
    @GetMapping("/userid")
    public User getUserByID(String id) {
        Long idLong = Long.valueOf(id);
        User userById = userServiceImpl.getUserById(idLong);
        return userById;
    }

    @PostMapping
    public User user(User user) throws UserAllreadyExistExeption, UserNotExist {
        User saveUser = userServiceImpl.saveUser(user);
        return saveUser;
    }
}
