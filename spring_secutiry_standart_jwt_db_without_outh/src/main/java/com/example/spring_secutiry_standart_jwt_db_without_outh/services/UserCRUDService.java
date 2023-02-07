package com.example.spring_secutiry_standart_jwt_db_without_outh.services;


import com.example.spring_secutiry_standart_jwt_db_without_outh.entities.User;
import com.example.spring_secutiry_standart_jwt_db_without_outh.exceptions.UserRuntimeException;
import com.example.spring_secutiry_standart_jwt_db_without_outh.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@EnableJpaRepositories(basePackages = {"com.example.spring_secutiry_standart_jwt_db_without_outh.repository"})
public class UserCRUDService {

    @Autowired
     private UserJpaRepository userJpaRepository;

    public List<User> findAllUsers() {
        List<User> allUsers = userJpaRepository.findAll();
        if(allUsers.isEmpty() || allUsers == null) {
            throw new UserRuntimeException(
                    500,
                    "users table is empty"
            );
        }
        return allUsers;
    }

    public User findUserByLogin(String login) {
        User userByLogin = userJpaRepository.findUserByLogin(login);
        if (userByLogin == null) {
            throw new UserRuntimeException(
                    500,
                    ("Cant find user with login : " + login)
            );
        }
        return userByLogin;
    }

    public User saveUser(User user) {
        System.out.println(user);
        User savedUser = userJpaRepository.save(user);
        if (savedUser == null) {
            throw new UserRuntimeException(
                    500,
                    "Saved user is null"
            );
        }
        return savedUser;
    }
}
