package com.example.rest_microservice_facade_feignclient.services;

import com.example.rest_microservice_facade_feignclient.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserLinkFeignClientService implements UserLinkService{

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> allUsers = userFeignClient
                .getAllUsers();
        return allUsers;
    }

    @Override
    public UserDTO getUserByLogin(String userLogin) {
        UserDTO userByLogin = userFeignClient
                .getUserByLogin(userLogin);
        return userByLogin;
    }

    @Override
    public UserDTO getUserById(UUID id) {
        UserDTO userById = userFeignClient.getUserById(id);
        return userById;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        UserDTO userFromMicroservice = userFeignClient
                .saveUser(userDTO);
        return userFromMicroservice;
    }
}
