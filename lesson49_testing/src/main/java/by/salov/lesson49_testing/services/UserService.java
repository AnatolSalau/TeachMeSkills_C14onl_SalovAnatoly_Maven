package by.salov.lesson49_testing.services;

import by.salov.lesson49_testing.domain.User;
import by.salov.lesson49_testing.exception.CantDeleteUserExeption;
import by.salov.lesson49_testing.exception.CantUpdateUserExeption;
import by.salov.lesson49_testing.exception.UserAllreadyExistExeption;
import by.salov.lesson49_testing.exception.UserNotExist;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByLogin(String login);
    User saveUser(User user) throws UserNotExist, UserAllreadyExistExeption;
    void saveDefaultUserWithLogin(String login);
    void saveUserWithTwoParams(String login);
    User updateUser(User user) throws UserNotExist, CantUpdateUserExeption;
    void deleteUser(User user) throws UserNotExist, CantDeleteUserExeption;
}
