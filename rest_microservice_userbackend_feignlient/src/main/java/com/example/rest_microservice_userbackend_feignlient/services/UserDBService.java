package com.example.rest_microservice_userbackend_feignlient.services;


import com.example.rest_microservice_userbackend_feignlient.dto.UserDTO;
import com.example.rest_microservice_userbackend_feignlient.entities.User;
import com.example.rest_microservice_userbackend_feignlient.exceptions.UserRuntimeException;
import com.example.rest_microservice_userbackend_feignlient.projections.UserProjection;
import com.example.rest_microservice_userbackend_feignlient.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserDBService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO getUserDTOById(UUID id) {
        UserProjection userById = userRepository.findUserById(id);
        UserDTO userDTO = new UserDTO(
                userById.getLogin(),
                userById.getPassword(),
                userById.getEmail()
        );
        return userDTO;
    }

    public UserDTO getUserDTOByLogin(String login) {
        UserProjection userByLogin = userRepository.findUserByLogin(login);
        //if userByLogin not found throw UserRuntimeException
        if(userByLogin == null) {
            throw new UserRuntimeException(500 ,"User with login " + login + " was not found");
        }
        UserDTO userDTO = new UserDTO(
                userByLogin.getLogin(),
                userByLogin.getPassword(),
                userByLogin.getEmail()
        );
        return userDTO;
    }

    public UserDTO saveUser(UserDTO user) {
        User newUser = new User(user.getLogin(),
                user.getPassword(),
                user.getEmail());
        //Wrap Runtime exception in custom UserRuntimeException
        User userById = null;
        try{
            userById = userRepository.save(newUser);
        } catch (RuntimeException exception) {
            throw new UserRuntimeException(500, "Cant save user : " + exception.getMessage());
        }

        UserDTO userDTO = new UserDTO(
                userById.getLogin(),
                userById.getPassword(),
                userById.getEmail()
        );
        return userDTO;
    }

    public List<UserDTO> getAllUsers() {
        List<UserProjection> allProjectedBy = userRepository.findAllProjectedBy();
        if(allProjectedBy == null || allProjectedBy.isEmpty()) {
            throw new RuntimeException("Users in DB were not found");
        }
        List<UserDTO> userDTOList = new ArrayList<>();
        for (UserProjection projection : allProjectedBy) {
            userDTOList.add(new UserDTO(projection.getLogin(),
                    projection.getPassword(),
                    projection.getEmail()));
        }
        return userDTOList;
    }
}
