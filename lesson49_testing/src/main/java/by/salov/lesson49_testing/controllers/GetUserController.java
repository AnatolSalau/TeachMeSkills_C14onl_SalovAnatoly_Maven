package by.salov.lesson49_testing.controllers;

import by.salov.lesson49_testing.domain.User;
import by.salov.lesson49_testing.services.UserService;
import by.salov.lesson49_testing.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class GetUserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    public List<User> getUserList() {
        return userServiceImpl.getAllUsers();
    }
}
