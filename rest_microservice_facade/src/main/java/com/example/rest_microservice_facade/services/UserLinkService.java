package com.example.rest_microservice_facade.services;

import com.example.rest_microservice_facade.dto.UserDTO;

import java.util.List;

public interface UserLinkService {
    List<UserDTO> getAllUsers();
    UserDTO getUserByLogin(String userLogin);
    UserDTO getUserById();
    UserDTO saveUser();
}
