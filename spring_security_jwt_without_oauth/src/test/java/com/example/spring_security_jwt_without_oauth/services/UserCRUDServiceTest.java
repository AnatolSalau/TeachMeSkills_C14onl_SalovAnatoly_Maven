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
        User newUser = new User(
                "user",
                "$2a$12$1Afgpcbxwgvc5Qj71/4gJeuC9bcoHl21vdLktqEThuzhL9TwmkDKG",
                RolesAll.USER
        );
        //User newAdmin = new User("admin", "admin", RolesAll.ADMIN);
        User user = userCRUDService.saveUser(newUser);
        Assertions.assertThat(user).isEqualTo(newUser);
    }
    @Test
    void saveAdmin() {
        User newAdmin = new User(
                "admin",
                "$2a$12$tPu4fDXy3qjFvM2o4.0MCOTOdX0IMHKP/krZ8dPmxBFAlPU2P8BNi",
                RolesAll.ADMIN
        );
        User user = userCRUDService.saveUser(newAdmin);
        Assertions.assertThat(user).isEqualTo(newAdmin);
    }
}