package com.example.rest_microservice_userbackend.services;

import com.example.rest_microservice_userbackend_feignlient.dto.UserDTO;
import com.example.rest_microservice_userbackend_feignlient.services.UserDBService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class UserDBServiceTest {

    @Autowired
    private UserDBService userDBService;

    @Test
    void testGetUserDTOById() {
        UserDTO userDTOFromDB = userDBService.getUserDTOById(
                UUID.fromString("09cd7534-c003-4aad-8703-c3f329b17377")
        );
        UserDTO userDTO = new UserDTO("firstLogin",
                "firstPassword", "firstemail@mail.ru");

        Assertions.assertEquals(userDTO, userDTOFromDB);
    }

    @Test
    void testGetUserDTOByLogin() {
        UserDTO userDTOFromDB = userDBService.getUserDTOByLogin("first2Login");

        UserDTO userDTO = new UserDTO("firstLogin",
                "firstPassword", "firstemail@mail.ru");

        Assertions.assertEquals(userDTO, userDTOFromDB);
    }

    @Test
    void testSaveUser() {
        UserDTO userDTO = new UserDTO("firstLogin",
                "firstPassword", "firstemail@mail.ru");
        UserDTO userDTOFromDB = userDBService.saveUser(userDTO);
        System.out.println(userDTOFromDB);

        Assertions.assertEquals(userDTO, userDTOFromDB);
    }

    @Test
    void testGetAllUsers() {
        List<UserDTO> allUsers = userDBService.getAllUsers();
        System.out.println(allUsers);
    }
}