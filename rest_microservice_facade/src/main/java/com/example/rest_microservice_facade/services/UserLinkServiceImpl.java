package com.example.rest_microservice_facade.services;

import com.example.rest_microservice_facade.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLinkServiceImpl implements UserLinkService {
    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public UserDTO getUserByLogin() {
        return null;
    }

    @Override
    public UserDTO getUserById() {
        return null;
    }

    @Override
    public UserDTO saveUser() {
        return null;
    }
}
