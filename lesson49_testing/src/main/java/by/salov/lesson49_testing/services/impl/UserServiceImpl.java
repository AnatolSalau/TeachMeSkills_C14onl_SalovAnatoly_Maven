package by.salov.lesson49_testing.services.impl;

import by.salov.lesson49_testing.domain.User;
import by.salov.lesson49_testing.exception.CantUpdateUserExeption;
import by.salov.lesson49_testing.exception.UserAllreadyExistExeption;
import by.salov.lesson49_testing.exception.UserNotExist;
import by.salov.lesson49_testing.repository.UserRepository;
import by.salov.lesson49_testing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidationImpl userValidation;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public User saveUser(User user) throws UserNotExist, UserAllreadyExistExeption {
        if (userValidation.isValidUserForSave(user)) {
            User byId = userRepository.getById(user.getId());
            if (byId == null) {
                User savedUser = userRepository.save(user);
                return savedUser;
            }
            else {
                throw  new UserAllreadyExistExeption("User allready exist");
            }
        } else {
            throw  new UserNotExist("Cant save user");
        }
    }

    @Override
    public User updateUser(User user) throws UserNotExist, CantUpdateUserExeption{
        if (userValidation.isValidUserForSave(user)) {
            User byId = userRepository.getById(user.getId());
            if (byId == null) {
                throw  new UserNotExist("User not exist");
            }
            else {
                User savedUser = userRepository.save(user);
                return savedUser;
            }
        } else {
            throw  new CantUpdateUserExeption("Cant update user");
        }
    }

    @Override
    public void deleteUser(User user) {

    }
}
