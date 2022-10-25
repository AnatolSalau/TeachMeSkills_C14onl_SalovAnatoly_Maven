package by.salov.lesson49_testing.services.impl;

import by.salov.lesson49_testing.domain.User;
import by.salov.lesson49_testing.exception.*;
import by.salov.lesson49_testing.repository.UserRepository;
import by.salov.lesson49_testing.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserValidationImpl userValidation;
    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        System.out.println(id);
        return userRepository.findById(id).get();
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public User saveUser(User user) throws  UserIDMustBeNull, CantUpdateUserExeption, UserNotValidExeption {
        if (userValidation.isValidUserForSave(user)) {
                if (user.getId() != null) {
                    throw new UserIDMustBeNull("Cant save user");
                }
            User save = userRepository.save(user);
            return save;
        }
        else {
            throw new UserNotValidExeption("Cant save user");
        }
    }

    @Override
    public User updateUser(User user) throws UserNotExist, CantUpdateUserExeption{
        if (userValidation.isValidUserForUpdate(user)) {
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
    public void deleteUser(User user) throws UserNotExist, CantDeleteUserExeption {
        if (userValidation.isValidUserForDelete(user)) {
            User byId = userRepository.getById(user.getId());
            if (byId == null) {
                throw  new UserNotExist("User not exist");
            }
            else {
                userRepository.delete(user);
            }
        }else {
            throw  new CantDeleteUserExeption("Cant update user");
        }
    }

    @Override
    public void saveDefaultUserWithLogin(String login) {
        User user = User.builder()
                .login(login)
                .password("default")
                .build();
        userRepository.save(user);
    }

    @Override
    public void saveUserWithTwoParams(String login) {
        String password = UUID.randomUUID().toString();
        if (userValidation.isValidParams(login, UUID.randomUUID().toString())) {
            User user = User.builder()
                    .login(login)
                    .password(password)
                    .build();
            userRepository.save(user);
        }
    }
}
