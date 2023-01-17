package com.example.rest_microservice_facade.services;

import com.example.rest_microservice_facade.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserLinkServiceImpl implements UserLinkService {
    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public UserDTO getUserByLogin(String userLogin) {
        return null;
    }


    @Override
    public UserDTO getUserById(UUID id) {
        return null;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        return null;
    }


}
