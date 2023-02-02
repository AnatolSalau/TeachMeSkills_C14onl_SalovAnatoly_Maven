package com.example.spring_security_jwt_without_oauth.services;

import com.example.spring_security_jwt_without_oauth.entities.User;
import com.example.spring_security_jwt_without_oauth.enums.RolesAll;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserCRUDServiceTest {
    @Autowired
    private UserCRUDService userCRUDService;

    @Test
    void findAllUsers() {
        List<User> allUsers = userCRUDService.findAllUsers();
        Assertions.assertThat(allUsers.size()).isEqualTo(1);
        System.out.println(allUsers);
    }

    @Test
    void saveUser() {
        //User newUser = new User("user", "user", RolesAll.USER);
        User newAdmin = new User("admin", "admin", RolesAll.ADMIN);
        User user = userCRUDService.saveUser(newAdmin);
        Assertions.assertThat(user).isEqualTo(newAdmin);
    }
}