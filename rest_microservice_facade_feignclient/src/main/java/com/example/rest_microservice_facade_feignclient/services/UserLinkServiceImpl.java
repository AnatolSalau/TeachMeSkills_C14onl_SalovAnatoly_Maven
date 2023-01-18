package com.example.rest_microservice_facade_feignclient.services;

import com.example.rest_microservice_facade_feignclient.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Rest template for link with other microservice
 */
@Service
public class UserLinkServiceImpl implements UserLinkService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final String GET_ALL_USERS = "http://127.0.0.1:8082/api/v2/user";
    private final String GET_USER_BY_LOGIN = "http://127.0.0.1:8082/api/v2/user";
    private final String GET_USER_BY_ID = "http://127.0.0.1:8082/api/v2/user/id";
    private final String SAVE_USER = "http://127.0.0.1:8082/api/v2/user";

    @Override
    public List<UserDTO> getAllUsers() {
        ResponseEntity<UserDTO[]> responseEntityFromBackend = restTemplate
                .getForEntity(GET_ALL_USERS, UserDTO[].class);
        UserDTO[] userDTOArray = responseEntityFromBackend.getBody();
        return Arrays.asList(userDTOArray);
    }

    @Override
    public UserDTO getUserByLogin(String userLogin) {
        ResponseEntity<UserDTO> responseEntityFromBackend = restTemplate
                .getForEntity(GET_USER_BY_LOGIN + "/" + userLogin, UserDTO.class);
        UserDTO userDTO = responseEntityFromBackend.getBody();
        return userDTO;
    }


    @Override
    public UserDTO getUserById(UUID id) {
        ResponseEntity<UserDTO> responseEntityFromBackend = restTemplate
                .getForEntity(GET_USER_BY_ID + "/" + id, UserDTO.class);
        UserDTO userDTO = responseEntityFromBackend.getBody();

        return userDTO;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        ResponseEntity<UserDTO> userDTOResponseEntity = restTemplate
                .postForEntity(SAVE_USER, userDTO , UserDTO.class);
        UserDTO userDTOFromDB = userDTOResponseEntity.getBody();
        return userDTOFromDB;
    }

}