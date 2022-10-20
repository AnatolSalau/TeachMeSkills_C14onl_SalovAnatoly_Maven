package by.salov.lesson49_testing.controllers;

import by.salov.lesson49_testing.domain.User;
import by.salov.lesson49_testing.exception.CantUpdateUserExeption;
import by.salov.lesson49_testing.exception.UserAllreadyExistExeption;
import by.salov.lesson49_testing.exception.UserIDMustBeNull;
import by.salov.lesson49_testing.exception.UserNotExist;
import by.salov.lesson49_testing.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/")
public class GetUserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/all")
    public List<User> getUserList() {
        return userServiceImpl.getAllUsers();
    }
    @GetMapping("/{id}")
    public User getUserByID(@RequestParam()String id) {
        Long idLong = Long.valueOf(id);
        User userById = userServiceImpl.getUserById(idLong);
        return userById;
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user) throws UserAllreadyExistExeption, UserNotExist, UserIDMustBeNull, CantUpdateUserExeption {
        User saveUser = userServiceImpl.saveUser(user);
        return saveUser;
    }
}
