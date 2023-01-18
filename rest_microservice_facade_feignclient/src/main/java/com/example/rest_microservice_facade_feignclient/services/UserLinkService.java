package com.example.rest_microservice_facade_feignclient.services;

import com.example.rest_microservice_facade_feignclient.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserLinkService {
    List<UserDTO> getAllUsers();
    UserDTO getUserByLogin(String userLogin);

    UserDTO getUserById(UUID id);
    UserDTO saveUser(UserDTO userDTO);

}
