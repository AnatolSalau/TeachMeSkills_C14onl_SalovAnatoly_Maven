package com.example.rest_microservice_userbackend.services;

import com.example.rest_microservice_userbackend.dto.UserDTO;
import com.example.rest_microservice_userbackend.entities.User;
import com.example.rest_microservice_userbackend.projections.UserProjection;
import com.example.rest_microservice_userbackend.repositories.UserRepository;
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
        UserProjection userById = userRepository.findUserByLogin(login);
        UserDTO userDTO = new UserDTO(
                userById.getLogin(),
                userById.getPassword(),
                userById.getEmail()
        );
        return userDTO;
    }

    public UserDTO saveUser(UserDTO user) {
        User newUser = new User(user.getLogin(),
                user.getPassword(),
                user.getEmail());
        User userById = userRepository.save(newUser);
        UserDTO userDTO = new UserDTO(
                userById.getLogin(),
                userById.getPassword(),
                userById.getEmail()
        );
        return userDTO;
    }

    public List<UserDTO> getAllUsers() {
        List<UserProjection> allProjectedBy = userRepository.findAllProjectedBy();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (UserProjection projection : allProjectedBy) {
            userDTOList.add(new UserDTO(projection.getLogin(),
                    projection.getPassword(),
                    projection.getEmail()));
        }
        return userDTOList;
    }
}
