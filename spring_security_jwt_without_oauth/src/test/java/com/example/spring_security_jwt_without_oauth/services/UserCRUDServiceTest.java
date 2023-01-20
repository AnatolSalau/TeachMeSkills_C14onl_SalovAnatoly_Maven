package com.example.spring_security_jwt_without_oauth.services;

import com.example.spring_security_jwt_without_oauth.entities.User;
import com.example.spring_security_jwt_without_oauth.enums.RolesAll;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserCRUDServiceTest {
    @Autowired
    private UserCRUDService userCRUDService;

    @Test
    void findAllUsers() {

    }

    @Test
    void saveUser() {
        User newUser = new User("user", "user", RolesAll.USER);
        User user = userCRUDService.saveUser(newUser);
        Assertions.assertThat(user).isEqualTo(newUser);
    }
}