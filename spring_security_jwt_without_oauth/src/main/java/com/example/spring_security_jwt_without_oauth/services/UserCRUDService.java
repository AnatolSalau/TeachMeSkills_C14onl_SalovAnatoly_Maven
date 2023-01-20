package com.example.spring_security_jwt_without_oauth.services;

import com.example.spring_security_jwt_without_oauth.entities.User;
import com.example.spring_security_jwt_without_oauth.exceptions.UserRuntimeException;
import com.example.spring_security_jwt_without_oauth.repository.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCRUDService {

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

    public User saveUser(User user) {
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
