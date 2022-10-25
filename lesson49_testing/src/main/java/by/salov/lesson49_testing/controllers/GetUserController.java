package by.salov.lesson49_testing.controllers;

import by.salov.lesson49_testing.domain.User;
import by.salov.lesson49_testing.exception.*;
import by.salov.lesson49_testing.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public User getUserByID(@PathVariable(name = "id") String id) {
        Long idLong = Long.valueOf(id);
        System.out.println("Hello from getUserByID");
         User userById = userServiceImpl.getUserById(idLong);
        System.out.println(userById);
        return userById;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserResponseEntityByID(@PathVariable(name = "id") String id) {
        Long idLong = Long.valueOf(id);
        User userFromDB = userServiceImpl.getUserById(idLong);
        if (userFromDB != null) {
            return ResponseEntity.ok(userFromDB);
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user) throws UserIDMustBeNull, CantUpdateUserExeption, UserNotValidExeption {
        User saveUser = userServiceImpl.saveUser(user);
        return saveUser;
    }
    @GetMapping("/test")
    public ModelAndView getTestTemplate() {
        ModelAndView modelAndView = new ModelAndView("test.html");
        return modelAndView;
    }
}
